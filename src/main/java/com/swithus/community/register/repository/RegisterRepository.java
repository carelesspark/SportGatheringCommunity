package com.swithus.community.register.repository;

import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<User, Long> {
    boolean existsByNickname(String userNickname);
    boolean existsByEmail(String userEmail);
}
