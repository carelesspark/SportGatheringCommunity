package com.swithus.community.manager.service;

import com.swithus.community.manager.dto.UserDTO;
import com.swithus.community.manager.dto.page.UserPageRequestDTO;
import com.swithus.community.manager.dto.page.UserPageResultDTO;
import com.swithus.community.user.entity.AuthId;

public interface UserService {

    UserPageResultDTO<UserDTO, AuthId> getUserList(UserPageRequestDTO requestDTO);

    default UserDTO entityToDto(AuthId authId){
        UserDTO dto = UserDTO.builder()
                .id(authId.getId())
                .userid(authId.getUserid())
                .userpwd(authId.getUserpwd())
                .regDate(authId.getRegDate())
                .userId(authId.getUser().getId())
                .email(authId.getUser().getEmail())
                .gender(authId.getUser().getGender())
                .name(authId.getUser().getName())
                .build();

        return dto;
    }
}
