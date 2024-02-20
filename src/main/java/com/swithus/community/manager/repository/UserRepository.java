package com.swithus.community.manager.repository;

import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AuthId, Long> {
}
