package com.swithus.community.login.repository;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.user.entity.AuthId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan(basePackages = "com.swithus.community.user.dto")
public class LoginRepositoryTests {

    @Autowired
    LoginRepository loginRepository;


    @Test
    public void check(@Autowired LoginDTO loginDTO){
        AuthId authId = AuthId.builder()
                .userid(loginDTO.getLoginId())
                .userpwd(loginDTO.getLoginPwd())
                .build();

        long result = loginRepository.countByUseridAndUserpwd(authId.getUserid(), authId.getUserpwd());
        // 만약 result 값이 1 이상이면 로그인 성공
        // 예: Assertions.assertTrue(result >= 1);
    };

}
