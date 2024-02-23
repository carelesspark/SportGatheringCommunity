package com.swithus.community.manager.repository;

import com.swithus.community.manager.entity.WithdrawalGathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WithdrawalGatheringRepository extends JpaRepository<WithdrawalGathering, Long>, QuerydslPredicateExecutor<WithdrawalGathering> {
}
