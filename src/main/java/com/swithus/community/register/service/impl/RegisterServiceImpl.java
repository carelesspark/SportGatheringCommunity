package com.swithus.community.register.service.impl;

import com.swithus.community.email.service.EmailService;
import com.swithus.community.global.exception.InvalidBirthException;
import com.swithus.community.register.repository.InputidRepository;
import com.swithus.community.register.repository.RegisterRepository;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.AuthVerification;
import com.swithus.community.user.entity.User;
import com.swithus.community.user.repository.AuthVerificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RegisterRepository registerRepository;
    private final InputidRepository inputidRepository;
    private final AuthVerificationRepository authVerificationRepository;
    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final EmailService emailService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;
    @Override
    public boolean join(UserDTO userDTO) {

        // 아이디 중복 검사
        if (isUserIdExists(userDTO.getUserid())) {
            throw new DuplicateFormatFlagsException("이미 존재하는 아이디입니다.");
        }

        // 닉네임 중복 검사
        if (isUserNicknameExists(userDTO.getNickname())) {
            throw new DuplicateFormatFlagsException("이미 존재하는 닉네임입니다.");
        }

        // 생년월일 검사
        if (userDTO.getBirth() == null || userDTO.getBirth().length() != 8) {
            throw new InvalidBirthException("생년월일은 8자리로 입력해주세요.");
        }


        // 중복 검사를 통과한 경우 회원가입 진행
        User user = User.builder()
                .name(userDTO.getName())
                .nickname(userDTO.getNickname())
                .email(userDTO.getEmail())
                .birth(userDTO.getBirth())
                .gender(userDTO.getGender())
                .build();

        registerRepository.save(user);

        AuthId authId = AuthId.builder()
                .userid(userDTO.getUserid())
                .userpwd(userDTO.getUserpwd())
                .user(user)
                .build();

        inputidRepository.save(authId);

        AuthVerification authVerification = AuthVerification.builder()
                .isEmailVerified(false)
                .verificationCode(userDTO.getVerificationCode())
                .user(user)
                .build();

        authVerificationRepository.save(authVerification);

        return true;
    }
    @Override
    public boolean isUserIdExists(String userId) {
        return inputidRepository.existsByUserid(userId);
    }

    @Override
    public boolean isUserNicknameExists(String userNickname) {
        return registerRepository.existsByNickname(userNickname);
    }

    @Override
    public boolean isUserEmailExists(String userEmail) {
        return registerRepository.existsByEmail(userEmail);
    }
}
