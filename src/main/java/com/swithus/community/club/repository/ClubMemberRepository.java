package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.repository.search.ClubMemberSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long>, ClubMemberSearchRepository {
    @Query("select cm " +
            "from ClubMember cm " +
            "where cm.club.id = :clubId and cm.member.id = :userId")
    ClubMember getClubMemberIdByClubAndUser(Long clubId, Long userId);

    @Modifying
    @Query("update ClubMember cm " +
            "set cm.nickname = :nickname " +
            "where cm.id = :clubMemberId")
    void updateNickname(Long clubMemberId, String nickname);
}
