package com.swithus.community.login.service;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.user.entity.AuthId;

public interface LoginService {
    long check(LoginDTO loginDTO);
    default AuthId DtoTOEntity (LoginDTO loginDTO){
        AuthId authentity = AuthId.builder()
                .userid(loginDTO.getLoginId())
                .userpwd(loginDTO.getLoginPwd())
                .build();
        return authentity;
    };

    default LoginDTO EntityToDto (AuthId entity){
        LoginDTO dto = LoginDTO.builder()
                .loginId(entity.getUserid())
                .loginPwd(entity.getUserpwd())
                .build();
        return dto;
    };
}
