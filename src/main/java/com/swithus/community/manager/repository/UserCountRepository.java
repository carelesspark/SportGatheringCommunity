package com.swithus.community.manager.repository;

import com.swithus.community.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserCountRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
    Long countBy();

    @Query(value = "SELECT COUNT(*) FROM user WHERE DATE_FORMAT(reg_date, '%Y-%m-%d') = DATE_FORMAT(DATE_ADD(CURRENT_DATE, INTERVAL 24 HOUR), '%Y-%m-%d')", nativeQuery = true)
    Long countTodayRegisteredUsers();
}
