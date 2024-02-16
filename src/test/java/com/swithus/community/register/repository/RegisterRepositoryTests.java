package com.swithus.community.register.repository;

import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan(basePackages = "com.swithus.community.user.dto")
public class RegisterRepositoryTests {

    @Autowired
    RegisterRepository registerRepository;
    @Test
    void join(@Autowired UserDTO userDTO){
        User user = User.builder()
                .name("Test Name")
                .nickname("TestNickname")
                .email("Test@Test.com")
                .birth("19970202")
                .gender("male")
                .build();

        registerRepository.save(user);
    }
}
