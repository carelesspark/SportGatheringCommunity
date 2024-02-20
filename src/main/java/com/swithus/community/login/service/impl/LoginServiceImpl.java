package com.swithus.community.login.service.impl;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.repository.LoginRepository;
import com.swithus.community.login.service.LoginService;
import com.swithus.community.user.entity.AuthId;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public Optional<AuthId> check(LoginDTO loginDTO) {
        // 사용자 입력으로부터 AuthId 객체 생성
        AuthId authId = AuthId.builder()
                .userid(loginDTO.getUserId())
                .userpwd(loginDTO.getUserPwd())
                .build();

        // 사용자 아이디로 저장된 AuthId를 찾기
        Optional<AuthId> optionalAuthId = loginRepository.findByUseridAndUserpwd(authId.getUserid(),authId.getUserpwd());

        // 저장된 AuthId가 없으면 로그인 실패
        if (optionalAuthId.isEmpty()) {
            log.info("로그인 실패: 사용자 아이디가 존재하지 않습니다.");
            return Optional.empty();
        }

        // 저장된 AuthId의 비밀번호와 입력된 비밀번호가 일치하는지 확인
        AuthId storedAuthId = optionalAuthId.get();
        if (!storedAuthId.getUserpwd().equals(authId.getUserpwd())) {
            log.info("로그인 실패: 비밀번호가 일치하지 않습니다.");
            return Optional.empty();
        }

        // 모든 검증을 통과하면 로그인 성공
        log.info("로그인 성공");
        return Optional.of(storedAuthId);
    }
}