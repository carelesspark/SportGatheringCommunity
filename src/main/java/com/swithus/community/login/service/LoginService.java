package com.swithus.community.login.service;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.user.entity.AuthId;

import java.util.Optional;

public interface LoginService {
    Optional<AuthId> check(LoginDTO loginDTO);
    //아이디찾기
    Optional<String> findUserId(String email, String name);
    default AuthId DtoTOEntity (LoginDTO loginDTO){
        AuthId authentity = AuthId.builder()
                .userid(loginDTO.getUserId())
                .userpwd(loginDTO.getUserPwd())
                .build();
        return authentity;
    };

    default LoginDTO EntityToDto (AuthId entity){
        LoginDTO dto = LoginDTO.builder()
                .userId(entity.getUserid())
                .userPwd(entity.getUserpwd())
                .build();
        return dto;
    };
}



