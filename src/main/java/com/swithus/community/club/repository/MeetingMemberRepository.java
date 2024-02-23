package com.swithus.community.club.repository;

import com.swithus.community.club.entity.MeetingMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, Long> {
    @Query("select count(mm) > 0 " +
            "from MeetingMember mm " +
            "where mm.meeting.id = :meetingId and mm.member.id = :clubMemberId")
    Boolean existsByMeetingAndClubMember(Long meetingId, Long clubMemberId);

    @Modifying
    @Query("delete " +
            "from MeetingMember mm " +
            "where mm.meeting.id = :meetingId and " +
            "mm.member.id = :clubMemberId")
    void deleteMeetingMember(Long meetingId, Long clubMemberId);
}
