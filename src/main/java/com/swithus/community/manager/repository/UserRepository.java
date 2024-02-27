package com.swithus.community.manager.repository;

import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<AuthId, Long>, QuerydslPredicateExecutor<AuthId> {
    @Transactional
    @Modifying
    @Query("DELETE FROM AuthId a WHERE a.user = :user")
    void deleteByUser(User user);
}
