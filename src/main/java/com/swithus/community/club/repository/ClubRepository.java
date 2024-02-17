package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Club;
import com.swithus.community.club.repository.search.ClubSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long>, ClubSearchRepository {
@Query("select c, count(distinct cm), ci " +
        "from Club c " +
        "left outer join ClubMember cm on cm.club = c " +
        "left outer join ClubImage ci on ci.club = c " +
        "where c.id = :id " +
        "group by c, ci")
    List<Object[]> getClubWithEveryImage(Long id);
}
