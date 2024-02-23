package com.swithus.community.manager.repository;

import com.swithus.community.board.entity.InquiryAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface InquiryAnswerRepository extends JpaRepository<InquiryAnswer, Long>, QuerydslPredicateExecutor<InquiryAnswer> {
    @Query("SELECT ia.id FROM InquiryAnswer ia WHERE ia.inquiry.id = :inquiryId")
    Long findAnswerIdByInquiryId(@Param("inquiryId") Long inquiryId);
}
