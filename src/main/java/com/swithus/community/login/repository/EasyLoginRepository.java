package com.swithus.community.login.repository;

import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EasyLoginRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}