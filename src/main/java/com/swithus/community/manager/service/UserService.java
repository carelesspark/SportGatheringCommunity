package com.swithus.community.manager.service;

import com.swithus.community.manager.dto.UserDTO;
import com.swithus.community.manager.dto.WithdrawalUserDTO;
import com.swithus.community.manager.dto.page.UserPageRequestDTO;
import com.swithus.community.manager.dto.page.UserPageResultDTO;
import com.swithus.community.manager.entity.WithdrawalUser;
import com.swithus.community.user.entity.AuthId;
import lombok.extern.log4j.Log4j2;

public interface UserService {

    UserPageResultDTO<UserDTO, AuthId> getUserList(UserPageRequestDTO requestDTO);

    UserDTO info(long no);

    void withdrawalUser(WithdrawalUserDTO withdrawalUserDTO);

    UserPageResultDTO<WithdrawalUserDTO, WithdrawalUser> getDeletedUserList(UserPageRequestDTO requestDTO);

    WithdrawalUserDTO infoDeletedUser(long no);

    Long countBy();

    Long countTodayUser();

    default UserDTO entityToDto(AuthId authId){
        UserDTO dto = UserDTO.builder()
                .id(authId.getId())
                .userid(authId.getUserid())
                .userpwd(authId.getUserpwd())
                .regDate(authId.getRegDate())
                .userDetailId(authId.getUser().getId())
                .email(authId.getUser().getEmail())
                .gender(authId.getUser().getGender())
                .nickname(authId.getUser().getNickname())
                .build();

        return dto;
    }

    default WithdrawalUser dtoToEntity(WithdrawalUserDTO withdrawalUserDTO){
        WithdrawalUser entity = WithdrawalUser.builder()
                .id(withdrawalUserDTO.getId())
                .deleteReason(withdrawalUserDTO.getDeleteReason())
                .email(withdrawalUserDTO.getEmail())
                .userId(withdrawalUserDTO.getUserId())
                .build();

        return entity;
    }

    default WithdrawalUserDTO entityToWithdrawalUserDTO(WithdrawalUser withdrawalUser){
        WithdrawalUserDTO dto = WithdrawalUserDTO.builder()
                .id(withdrawalUser.getId())
                .regDate(withdrawalUser.getRegDate())
                .deleteReason(withdrawalUser.getDeleteReason())
                .email(withdrawalUser.getEmail())
                .userId(withdrawalUser.getUserId())
                .build();

        return dto;
    }
}
