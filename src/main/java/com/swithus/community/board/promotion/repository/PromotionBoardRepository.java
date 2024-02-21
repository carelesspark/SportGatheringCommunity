package com.swithus.community.board.promotion.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.swithus.community.board.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromotionBoardRepository extends JpaRepository<Promotion,Long> , QuerydslPredicateExecutor<Promotion> {

    // 어떤 게시글의 작성자가 누구인가?
    @Query("SELECT p,w FROM Promotion p LEFT JOIN p.writer w WHERE p.id = :id")
    Object getBoardWithWriter(@Param("id") Long id);


    // 어떤 게시글의 종목이 어디인가?
    @Query("SELECT p,s FROM Promotion p LEFT JOIN p.sports s WHERE p.id = :id")
    Object getBoardWithSports(@Param("id") Long id);


    // 어떤 게시글의 댓글을 출력.
    @Query("SELECT p,r FROM Promotion p LEFT JOIN PromotionReply r ON r.promotion = p WHERE p.id = :id")
    List<Object[]> getBoardWithReply(@Param("id") Long id);


    // 어떤 게시물의 댓글개수를 출력.
    @Query(value = "SELECT p,w,count(r) FROM Promotion p" +
                    " LEFT JOIN p.writer w" +
                    " LEFT JOIN PromotionReply r ON r.promotion = p" +
                    " GROUP BY p",
            countQuery = "SELECT count(p) FROM Promotion p"
           )
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

}
