//package com.swithus.community.login.repository;
//
//import com.swithus.community.login.dto.LoginDTO;
//import com.swithus.community.user.entity.AuthId;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ComponentScan(basePackages = "com.swithus.community.user.dto")
//public class LoginRepositoryTests {
//
//    @Autowired
//    LoginRepository loginRepository;
//
//
//    @Test
//    public void check(@Autowired LoginDTO loginDTO){
//        AuthId authId = AuthId.builder()
//                .userid("test1")
//                .userpwd("1234")
//                .build();
//
//        loginRepository.findByLoginId(authId.getUserid());
//        // 만약 result 값이 1 이상이면 로그인 성공
//        // 예: Assertions.assertTrue(result >= 1);
//    };
//
//}


//package com.swithus.community.login.repository;
//
//import com.swithus.community.login.dto.LoginDTO;
//import com.swithus.community.user.entity.AuthId;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ComponentScan(basePackages = "com.swithus.community.user.dto")
//public class LoginRepositoryTests {
//
//    @Autowired
//    private LoginRepository loginRepository;
//
//    @Test
//    public void check() {
//        // Given
//        AuthId authId = AuthId.builder()
//                .userid("test1")
//                .userpwd("1234")
//                .build();
//
//        // When
//        Optional<AuthId> result = loginRepository.findByUserid(authId.getUserid());
//
//        // Then
//        assertTrue(result.isPresent(), "로그인 성공");
//    }
//}


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
                .userid("user1")
                .userpwd("1234") // 올바른 비밀번호를 사용해보세요


        // When
        Optional<AuthId> result = loginRepository.findByUseridAndUserpwd(authId.getUserid(), authId.getUserpwd());

        // Then
        assertFalse(result.isPresent(), "로그인 실패");
    }
}

