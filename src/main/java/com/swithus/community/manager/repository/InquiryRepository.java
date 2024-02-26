package com.swithus.community.manager.repository;

import com.swithus.community.board.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, QuerydslPredicateExecutor<Inquiry> {
    Long countByIsAnsweredFalse();
}
