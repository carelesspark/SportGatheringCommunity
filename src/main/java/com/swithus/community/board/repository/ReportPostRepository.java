package com.swithus.community.board.repository;

import com.swithus.community.manager.entity.ReportPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface ReportPostRepository extends JpaRepository<ReportPost, Long>, QuerydslPredicateExecutor<ReportPost> {
    @Query("SELECT COUNT(*) FROM ReportPost WHERE nickname = :nickname AND post.id = :postId")
    Long countByNicknameAndPostId(String nickname, Long postId);
}
