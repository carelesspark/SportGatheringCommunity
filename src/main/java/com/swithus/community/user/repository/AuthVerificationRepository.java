package com.swithus.community.user.repository;

import com.swithus.community.user.entity.AuthVerification;
import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthVerificationRepository extends JpaRepository<AuthVerification, Long> {
    AuthVerification findByUser(User user);
    AuthVerification findByVerificationCode(String verificationCode);
}
