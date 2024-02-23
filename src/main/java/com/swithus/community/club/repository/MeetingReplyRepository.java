package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Meeting;
import com.swithus.community.club.entity.MeetingReply;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingReplyRepository extends JpaRepository<MeetingReply, Long> {
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<MeetingReply> findByMeeting(Meeting meeting);
}
