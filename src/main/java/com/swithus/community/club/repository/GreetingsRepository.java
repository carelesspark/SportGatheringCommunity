package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.repository.search.GreetingsSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GreetingsRepository extends JpaRepository<Greetings, Long>, GreetingsSearchRepository {
    @Query("select g, gi " +
            "from Greetings g " +
            "left outer join GreetingsImage gi on gi.greetings = g " +
            "left outer join g.member as cm " +
            "where cm.club.id = :clubId and cm.member.id = :userId ")
    List<Object[]> getGreetingsByClubAndUser(Long clubId, Long userId);

    @Query("select g, count(distinct gl) " +
            "from Greetings g " +
            "left join GreetingsLike gl on gl.greetings = g " +
            "where g.id = :greetingsId " +
            "group by g")
    List<Object[]> getGreetingsAndLikeCountByGreetingsId(Long greetingsId);

    @Modifying
    @Query("update Greetings g set g.content = :content where g.id = :greetingsId")
    void updateGreetings(Long greetingsId, String content);
}
