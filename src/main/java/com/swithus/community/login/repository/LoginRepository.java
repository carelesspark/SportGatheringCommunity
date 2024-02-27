package com.swithus.community.login.repository;

import com.swithus.community.user.entity.AuthId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<AuthId, Long> {
    Optional<AuthId> findByUseridAndUserpwd(String userid, String userpwd);

}