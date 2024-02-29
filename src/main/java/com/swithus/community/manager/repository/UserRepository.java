package com.swithus.community.manager.repository;

import com.swithus.community.user.entity.AuthId;
import com.swithus.community.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<AuthId, Long>, QuerydslPredicateExecutor<AuthId> {
    @Transactional
    @Modifying
    @Query("DELETE FROM AuthId a WHERE a.user = :user")
    void deleteByUser(User user);


    @Query("SELECT COUNT(u) FROM User u WHERE FUNCTION('DATE_FORMAT', u.regDate, '%Y-%m-%d') = CURRENT_DATE")
    Long countByNow();

    @Query("SELECT COUNT(u) FROM User u WHERE FUNCTION('DATE_FORMAT', u.regDate, '%Y-%m-%d') = FUNCTION('DATE_FORMAT', CURRENT_DATE - 1, '%Y-%m-%d')")
    Long countByNowInterval1Day();

    @Query("SELECT COUNT(u) FROM User u WHERE FUNCTION('DATE_FORMAT', u.regDate, '%Y-%m-%d') = FUNCTION('DATE_FORMAT', CURRENT_DATE - 2, '%Y-%m-%d')")
    Long countByNowInterval2Day();

    @Query("SELECT COUNT(u) FROM User u WHERE FUNCTION('DATE_FORMAT', u.regDate, '%Y-%m-%d') = FUNCTION('DATE_FORMAT', CURRENT_DATE - 3, '%Y-%m-%d')")
    Long countByNowInterval3Day();

    @Query("SELECT COUNT(u) FROM User u WHERE FUNCTION('DATE_FORMAT', u.regDate, '%Y-%m-%d') = FUNCTION('DATE_FORMAT', CURRENT_DATE - 4, '%Y-%m-%d')")
    Long countByNowInterval4Day();
}
