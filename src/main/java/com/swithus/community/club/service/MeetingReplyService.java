package com.swithus.community.club.service;

import com.swithus.community.club.dto.MeetingReplyDTO;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.entity.Meeting;
import com.swithus.community.club.entity.MeetingReply;

import java.util.List;

public interface MeetingReplyService {
    List<MeetingReplyDTO> getMeetingReplyDTOListByMeeting(Long meetingId);

    Long register(MeetingReplyDTO meetingReplyDTO);

    void updateReply(MeetingReplyDTO meetingReplyDTO);

    void deleteReply(Long meetingReplyId);

    default MeetingReplyDTO entityToMeetingReplyDTO(MeetingReply reply) {
        return MeetingReplyDTO.builder()
                .meetingReplyId(reply.getId())
                .meetingId(reply.getMeeting().getId())
                .memberId(reply.getMember().getId())
                .memberName(reply.getMember().getMember().getName())
                .memberNickname(reply.getMember().getNickname())
                .comment(reply.getComment())
                .regDate(reply.getRegDate())
                .build();
    }

    default MeetingReply meetingReplyDTOToMeetingReply(MeetingReplyDTO dto) {
        return MeetingReply.builder()
                .id(dto.getMeetingReplyId())
                .meeting(Meeting.builder()
                        .id(dto.getMeetingId())
                        .build())
                .member(ClubMember.builder()
                        .id(dto.getMemberId())
                        .build())
                .comment(dto.getComment())
                .build();
    }
}
