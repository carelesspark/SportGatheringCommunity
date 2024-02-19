package com.swithus.community.login.repository;

import com.swithus.community.user.entity.AuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<AuthId,Long> {
    long countByUseridAndUserpwd(String userid, String userpwd);
}
