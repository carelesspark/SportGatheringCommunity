package com.swithus.community.user.repository;

import com.swithus.community.user.entity.AuthVerification;
import com.swithus.community.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthVerificationRepository extends JpaRepository<AuthVerification, Long> {
    AuthVerification findByUser(User user);
    AuthVerification findByVerificationCode(String verificationCode);

    @Transactional
    @Modifying
    @Query("DELETE FROM AuthVerification a WHERE a.user = :user")
    void deleteByUser(User user);
}
