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
import org.springframework.transaction.annotation.Transactional;

import java.util.DuplicateFormatFlagsException;

@Service
@Log4j2
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RegisterRepository registerRepository;
    private final InputidRepository inputidRepository;

    @Override
    public void join(UserDTO userDTO){

        if(isUserIdExists(userDTO.getUserid())) {
            throw new DuplicateFormatFlagsException("중복된 아이디입니다.");
        }

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

    }

    @Override
    public boolean isUserIdExists(String userId) {
        return inputidRepository.existsByUserid(userId);
    }

}
