package com.swithus.community.manager.repository;

import com.swithus.community.manager.entity.WithdrawalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WithdrawalUserRepository extends JpaRepository<WithdrawalUser, Long>, QuerydslPredicateExecutor<WithdrawalUser> {
}
