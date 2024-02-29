package com.swithus.community.register.service;

import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;

public interface RegisterService {
    boolean join(UserDTO userDTO);
    boolean isUserIdExists(String userId);
    boolean isUserNicknameExists(String userNickname);
    boolean isUserEmailExists(String userEmail);
    default User dtoToEntity (UserDTO dto){
        User entity = User.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .gender(dto.getGender())
                .build();
        return entity;
    }

    default UserDTO EntityToDto (User entity) {
        UserDTO dto = UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .nickname(entity.getNickname())
                .email(entity.getEmail())
                .birth(entity.getBirth())
                .gender(entity.getGender())
                .addr(entity.getAddr())
                .addrDetail(entity.getAddrDetail())
                .post(entity.getPost())
                .isLeader(entity.getIsLeader())
                .build();
        return dto;
    }
    default AuthId dtoToAuthEntity (UserDTO dto){
        AuthId authentity = AuthId.builder()
                .id(dto.getId())
                .userid(dto.getUserid())
                .userpwd(dto.getUserpwd())
                .build();
        return authentity;
    }

    default UserDTO AuthEntityToDto (AuthId entity) {
        User user = entity.getUser();

        UserDTO dto = UserDTO.builder()
                .userid(entity.getUserid())
                .userpwd(entity.getUserpwd())
                .id(user.getId())
                .build();
        return dto;
    }

}
