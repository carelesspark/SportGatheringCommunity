package com.swithus.community.user.repository;

import com.swithus.community.manager.repository.UserDetailRepository;
import com.swithus.community.manager.repository.UserRepository;
import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class FindMemberRepositoryTest {
    @Autowired
    UserDetailRepository userRepository;
    @Autowired
    UserRepository authIdRepository;

    @Test
    void insertUserTest(){
        IntStream.rangeClosed(1,100).forEach(i->{
            User user = User.builder()
                    .name("User "+i)
                    .nickname("Nickname "+i)
                    .email("user"+i+"@swu.com")
                    .birth("15570707")
                    .gender("male")
                    .addr("대한민국 서울특별시 종로구 청와대로 1")
                    .addrDetail("101동 101호")
                    .post("03048")
                    .isLeader((byte) 0)
                    .build();

            userRepository.save(user);

            AuthId authId=AuthId.builder()
                    .user(User.builder().id(user.getId()).build())
                    .userid(String.valueOf(i))
                    .userpwd("1")
                    .build();

            authIdRepository.save(authId);
        });
    }
}
