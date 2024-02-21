package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.repository.search.GreetingsSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GreetingsRepository extends JpaRepository<Greetings, Long>, GreetingsSearchRepository {
    @Query("select g, gi " +
            "from Greetings g " +
            "left outer join GreetingsImage gi on gi.greetings = g " +
            "left outer join g.member as cm " +
            "where cm.club.id = :clubId and cm.member.id = :userId ")
    List<Object[]> getGreetingsByClubAndUser(Long clubId, Long userId);
}
