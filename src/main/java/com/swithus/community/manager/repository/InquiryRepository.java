package com.swithus.community.manager.repository;

import com.swithus.community.board.entity.Inquiry;
import com.swithus.community.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, QuerydslPredicateExecutor<Inquiry> {
    Long countByIsAnsweredFalse();

    Inquiry findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Inquiry i WHERE i.user = :user")
    void deleteByUser(User user);
}
