package com.swithus.community.register.repository;

import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputidRepository extends JpaRepository<AuthId, Long> {
}
