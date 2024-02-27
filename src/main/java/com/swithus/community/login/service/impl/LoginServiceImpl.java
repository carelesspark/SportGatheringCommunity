package com.swithus.community.login.service.impl;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.repository.LoginRepository;
import com.swithus.community.login.service.LoginService;
import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.AuthVerification;
import com.swithus.community.user.entity.User;
import com.swithus.community.user.repository.AuthIdRepository;
import com.swithus.community.user.repository.AuthVerificationRepository;
import com.swithus.community.user.repository.FindMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final FindMemberRepository findMemberRepository;
    private final AuthIdRepository authIdRepository;
    private final AuthVerificationRepository authVerificationRepository;
    @Override
    public Optional<AuthId> check(LoginDTO loginDTO) {
        // 사용자 입력으로부터 AuthId 객체 생성
        AuthId authId = AuthId.builder()
                .userid(loginDTO.getUserId())
                .userpwd(loginDTO.getUserPwd())
                .build();

        // 사용자 아이디로 저장된 AuthId를 찾기
        Optional<AuthId> result = loginRepository.findByUseridAndUserpwd(authId.getUserid(),authId.getUserpwd());

        // 저장된 AuthId가 없으면 로그인 실패
        if (result.isEmpty()) {
            log.info("로그인 실패: 사용자 아이디가 존재하지 않습니다.");
            return Optional.empty();
        }

        // 저장된 AuthId의 비밀번호와 입력된 비밀번호가 일치하는지 확인
        AuthId value = result.get();
        if (!value.getUserpwd().equals(authId.getUserpwd())) {
            log.info("로그인 실패: 비밀번호가 일치하지 않습니다.");
            return Optional.empty();
        }

        // 모든 검증을 통과하면 로그인 성공
        log.info("로그인 성공 id: "+value.getUserid()+" pwd:"+value.getUserpwd());
        log.info(Optional.of(value));
        return Optional.of(value);
    }

    @Override
    public Optional<String> findUserId(String email, String name) {
        Optional<User> foundUser = Optional.ofNullable(findMemberRepository.findByEmailAndName(email, name));

        if (foundUser.isPresent()) {
            User user = foundUser.get();
            AuthId authId = authIdRepository.findByUser(user);

            // 인증 여부 확인
            AuthVerification authVerification = authVerificationRepository.findByUser(user);
            if (authVerification != null && authVerification.isEmailVerified()) {
                // verification_code와 is_email_verified 값을 초기화
                authVerification.setVerificationCode(null);
                authVerification.setIsEmailVerified(false);
                authVerificationRepository.save(authVerification);

                return Optional.ofNullable(authId != null ? authId.getUserid() : null);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    private boolean isEmailVerified(User user) {
        AuthVerification authVerification = authVerificationRepository.findByUser(user);
        return authVerification != null && authVerification.isEmailVerified();
    }
}