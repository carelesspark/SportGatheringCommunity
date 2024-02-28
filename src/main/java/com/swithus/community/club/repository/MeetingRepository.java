package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Meeting;
import com.swithus.community.club.entity.MeetingMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query("select m, count(distinct mm) " +
            "from Meeting m " +
            "left join MeetingMember mm on mm.meeting = m " +
            "where m.club.id = :clubId " +
            "and m.mTime > :now " +
            "group by m")
    List<Object[]> getMeetingDTOListByClubIdAndNow(Long clubId, LocalDateTime now);

    @Query("select m, count(distinct mm) " +
            "from Meeting m " +
            "left join MeetingMember mm on mm.meeting = m " +
            "where m.club.id = :clubId " +
            "and m.ctgr.id = :meetingCtgrId " +
            "and m.mTime > :now " +
            "group by m")
    List<Object[]> getMeetingDTOListByClubIdAndCtgrIdAndNow(Long clubId, Long meetingCtgrId, LocalDateTime now);

    @Query("select m, count(distinct mm), count(distinct mr) " +
            "from Meeting m " +
            "left join MeetingMember mm on mm.meeting = m " +
            "left join MeetingReply mr on mr.meeting = m " +
            "where m.id = :meetingId " +
            "group by m")
    List<Object[]> getMeetingDTOByMeetingId(Long meetingId);

    @Query("select mm " +
            "from MeetingMember mm " +
            "where mm.meeting.id = :meetingId")
    List<MeetingMember> getMeetingMemberListByMeetingId(Long meetingId);
}
