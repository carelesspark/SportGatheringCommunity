package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Club;
import com.swithus.community.club.repository.search.ClubSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long>, ClubSearchRepository {
    @Query("select c, count(distinct cm), ci " +
            "from Club c " +
            "left outer join ClubMember cm on cm.club = c " +
            "left outer join ClubImage ci on ci.club = c " +
            "where c.id = :id " +
            "group by c, ci")
    List<Object[]> getClubWithEveryImage(Long id);

    @Query("select c, cm " +
            "from Club c " +
            "left outer join ClubMember cm on cm.club = c " +
            "where c.id = :clubId and cm.member.id = :userId")
    List<Object[]> getClubAndClubMemberByClubAndUser(Long clubId, Long userId);

    @Query("select c, cm " +
            "from Club c " +
            "left join ClubMember cm on cm.club = c " +
            "where c.id = :clubId and cm.id = :clubMemberId")
    List<Object[]> getClubAndClubMemberByClubIdAndClubMemberId(Long clubId, Long clubMemberId);

    @Query("select c, count(distinct cm), count(distinct m), ci " +
            "from Club c " +
            "left join ClubMember cm on cm.club = c " +
            "left join Meeting m on m.club = c " +
            "left outer join ClubImage ci on ci.club = c " +
            "where m.mTime > :currentDateTime " +
            "group by c, ci " +
            "order by count(distinct m) desc, " +
            "count(distinct cm) desc " +
            "limit :number")
    List<Object[]> getClubAndMemberCountAndMeetingCountAndImageLimitByNumber(LocalDateTime currentDateTime, int number);
}
