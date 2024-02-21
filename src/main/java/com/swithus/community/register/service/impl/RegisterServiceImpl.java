package com.swithus.community.register.service.impl;

import com.swithus.community.register.repository.InputidRepository;
import com.swithus.community.register.repository.RegisterRepository;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;

@Service
@Log4j2
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RegisterRepository registerRepository;
    private final InputidRepository inputidRepository;

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

}
