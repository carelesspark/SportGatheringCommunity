package com.swithus.community.register.repository;

import com.swithus.community.user.entity.AuthId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputidRepository extends JpaRepository<AuthId, Long> {
    boolean existsByUserid(String userId);
}
