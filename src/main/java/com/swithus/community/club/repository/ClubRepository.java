package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Club;
import com.swithus.community.club.repository.search.ClubSearchRepository;
import org.springframework.data.domain.Pageable;
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
            "left join ClubImage ci on ci.club = c " +
            "where m.mTime > :currentDateTime " +
            "group by c " +
            "order by count(distinct m) desc, " +
            "count(distinct cm) desc ")
    List<Object[]> getClubAndMemberCountAndMeetingCountAndImageLimitByNumber(LocalDateTime currentDateTime, Pageable pageable);


    @Query("SELECT c FROM Club c LEFT JOIN User u on c.leader = u WHERE u.nickname = :nickname")
    List<Club> findByUserNickname(String nickname);

    @Query("SELECT COUNT(c) FROM Club c LEFT JOIN Sports s on c.sports = s WHERE s.name = '축구'")
    Long countBySportsWhichNameSoccer();

    @Query("SELECT COUNT(c) FROM Club c LEFT JOIN Sports s on c.sports = s WHERE s.name = '야구'")
    Long countBySportsWhichNameBaseball();

    @Query("SELECT COUNT(c) FROM Club c LEFT JOIN Sports s on c.sports = s WHERE s.name = '농구'")
    Long countBySportsWhichNameBasketball();

    @Query("SELECT COUNT(c) FROM Club c LEFT JOIN Sports s on c.sports = s WHERE s.name = '탁구'")
    Long countBySportsWhichNameTable_Tennis();

    @Query("SELECT COUNT(c) FROM Club c LEFT JOIN Sports s on c.sports = s WHERE s.name = '배드민턴'")
    Long countBySportsWhichNameBadminton();
}
