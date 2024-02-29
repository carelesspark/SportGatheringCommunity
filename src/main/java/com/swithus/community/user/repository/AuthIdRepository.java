package com.swithus.community.user.repository;

import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthIdRepository extends JpaRepository<AuthId, Long> {
    AuthId findByUser(User foundUser);

    AuthId findByUserid(String userId);
}
