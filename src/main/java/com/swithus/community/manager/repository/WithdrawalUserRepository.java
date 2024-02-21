package com.swithus.community.manager.repository;

import com.swithus.community.manager.entity.WithdrawalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalUserRepository extends JpaRepository<WithdrawalUser, Long> {
}
