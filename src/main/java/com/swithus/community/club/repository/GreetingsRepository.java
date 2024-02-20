package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.repository.search.GreetingsSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GreetingsRepository extends JpaRepository<Greetings, Long>, GreetingsSearchRepository {
    @Query("select g, count(distinct gl), gi " +
            "from Greetings g " +
            "left outer join GreetingsImage gi on gi.greetings = g " +
            "left outer join GreetingsLike gl on gl.greetings = g " +
            "left outer join g.member cm " +
            "where cm.club.id = :clubId and cm.member.id = :userId " +
            "group by g, gi")
    Object[] getGreetingsByClubAndUser(Long clubId, Long userId);
}
