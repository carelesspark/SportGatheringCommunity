package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClubPostLikeRepository extends JpaRepository<ClubPostLike, Long> {
    @Query("select count(l) > 0 " +
            "from ClubPostLike l " +
            "where l.post.id = :postId and l.member.id = :clubMemberId")
    boolean checkExists(Long postId, Long clubMemberId);
}
