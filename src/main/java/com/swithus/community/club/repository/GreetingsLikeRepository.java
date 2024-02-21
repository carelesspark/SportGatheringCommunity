package com.swithus.community.club.repository;

import com.swithus.community.club.entity.GreetingsLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GreetingsLikeRepository extends JpaRepository<GreetingsLike, Long> {
    @Modifying
    @Transactional
    @Query("delete from GreetingsLike gl where gl.greetings.id = :greetingsId and gl.member.id = :clubMemberId")
    void deleteByGreetingsIdAndClubMemberId(Long greetingsId, Long clubMemberId);

    @Query("select count(gl) > 0 " +
            "from GreetingsLike gl " +
            "where gl.greetings.id = :greetingsId and gl.member.id = :clubMemberId")
    boolean existsByGreetingsIdAndClubMemberId(Long greetingsId, Long clubMemberId);
}
