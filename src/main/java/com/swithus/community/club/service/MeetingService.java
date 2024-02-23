package com.swithus.community.club.service;

import com.swithus.community.club.dto.MeetingDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingService {
    List<MeetingDTO> getActiveMeetingDTOList(Long clubId, Long meetingCtgrId, LocalDateTime now);

    void createMeeting(MeetingDTO meetingDTO);

    void updateMeeting(MeetingDTO meetingDTO);

    MeetingDTO getMeetingDTO(Long meetingId);

    Boolean existsMeetingMember(Long meetingId, Long clubMemberId);

    void insertMeetingMember(Long meetingId, Long clubMemberId);

    void deleteMeetingMember(Long meetingId, Long clubMemberId);
}
