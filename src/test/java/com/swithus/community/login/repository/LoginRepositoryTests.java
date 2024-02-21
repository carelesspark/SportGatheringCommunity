package com.swithus.community.login.repository;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.user.entity.AuthId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan(basePackages = "com.swithus.community.login.repository")
public class LoginRepositoryTests {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    public void check() {
        // Given
        AuthId authId = AuthId.builder()
                .userid("testjb")
                .userpwd("1234")
                .build();// 올바른 비밀번호를 사용해보세요//테스트를 위해 객체 생성
        // When
        Optional<AuthId> result = loginRepository.findByUseridAndUserpwd(authId.getUserid(), authId.getUserpwd());

        // Then
        //assertFalse(result.isPresent(), "로그인 실패");
        if(result.isPresent()){
            //System.out.println(result);
            System.out.println("로그인 성공");
            AuthId value = result.get();
            System.out.println("id: "+value.getUserid()+", pwd: "+value.getUserpwd());
        }else{
            System.out.println("로그인 실패");
        }
    }
}

