package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.MeetingReplyDTO;
import com.swithus.community.club.entity.Meeting;
import com.swithus.community.club.entity.MeetingReply;
import com.swithus.community.club.repository.MeetingReplyRepository;
import com.swithus.community.club.service.MeetingReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MeetingReplyServiceImpl implements MeetingReplyService {
    private final MeetingReplyRepository replyRepository;

    @Override
    public List<MeetingReplyDTO> getMeetingReplyDTOListByMeeting(Long meetingId) {
        Meeting meeting = Meeting.builder().id(meetingId).build();

        List<MeetingReply> resultList = replyRepository.findByMeeting(meeting);

        return resultList.stream().map(this::entityToMeetingReplyDTO).toList();
    }

    @Override
    public Long register(MeetingReplyDTO meetingReplyDTO) {
        MeetingReply reply = meetingReplyDTOToMeetingReply(meetingReplyDTO);
        replyRepository.save(reply);

        return reply.getId();
    }

    @Override
    public void updateReply(MeetingReplyDTO meetingReplyDTO) {
        MeetingReply reply = meetingReplyDTOToMeetingReply(meetingReplyDTO);

        replyRepository.save(reply);
    }

    @Override
    public void deleteReply(Long meetingReplyId) {
        replyRepository.deleteById(meetingReplyId);
    }
}
