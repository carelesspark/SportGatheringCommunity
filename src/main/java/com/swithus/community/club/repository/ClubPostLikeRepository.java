package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubPostLikeRepository extends JpaRepository<ClubPostLike, Long> {
    @Query("select l " +
            "from ClubPostLike l " +
            "where l.post.id = :postId")
    List<ClubPostLike> getLikeListByClubPostId(Long postId);

    @Query("select count(l) > 0 " +
            "from ClubPostLike l " +
            "where l.post.id = :postId and l.member.id = :clubMemberId")
    boolean checkExists(Long postId, Long clubMemberId);

    @Modifying
    @Query("DELETE FROM ClubPostLike l " +
            "WHERE l.post.id = :postId " +
            "AND l.member.id = :clubMemberId")
    void deleteByPostIdAndClubMemberId(Long postId, Long clubMemberId);
}
