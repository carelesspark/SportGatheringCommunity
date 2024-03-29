package com.swithus.community.board.repository;

import com.swithus.community.board.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PromotionBoardRepository extends JpaRepository<Promotion,Long> , QuerydslPredicateExecutor<Promotion> {
    List<Promotion> findTop4ByOrderByRegDateDesc();

    @Query("SELECT p FROM Promotion p LEFT JOIN User u on p.writer = u WHERE u.nickname = :nickname")
    List<Promotion> findByUserNickname(String nickname);



//    // 어떤 게시글의 작성자가 누구인가?
//    @Query("SELECT p,w FROM Promotion p LEFT JOIN p.writer w WHERE p.id = :id")
//    Object getBoardWithWriter(@Param("id") Long id);
//
//
//    // 어떤 게시글의 종목이 어디인가?
//    @Query("SELECT p,s FROM Promotion p LEFT JOIN p.sports s WHERE p.id = :id")
//    Object getBoardWithSports(@Param("id") Long id);
//
//
//    // 어떤 게시글의 댓글을 출력.
//    @Query("SELECT p,r FROM Promotion p LEFT JOIN PromotionReply r ON r.promotion = p WHERE p.id = :id")
//    List<Object[]> getBoardWithReply(@Param("id") Long id);
//
//
//    // 어떤 게시물의 댓글개수를 출력.
//    @Query(value = "SELECT p,w,count(r) FROM Promotion p" +
//                    " LEFT JOIN p.writer w" +
//                    " LEFT JOIN PromotionReply r ON r.promotion = p" +
//                    " GROUP BY p",
//            countQuery = "SELECT count(p) FROM Promotion p"
//           )
//    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

}
