package com.swithus.community.login.service.impl;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.repository.LoginRepository;
import com.swithus.community.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.AuthId;

@Service
@Log4j2
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    public long check(LoginDTO loginDTO) {
        AuthId authId = AuthId.builder()
                .userid(loginDTO.getLoginId())
                .userpwd(loginDTO.getLoginPwd())
                .build();

        return loginRepository.countByUseridAndUserpwd(authId.getUserid(), authId.getUserpwd());//조회중 하나라도 있으면 1반환(로그인성공)
    };
}
