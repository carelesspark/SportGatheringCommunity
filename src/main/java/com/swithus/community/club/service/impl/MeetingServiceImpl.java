package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.MeetingDTO;
import com.swithus.community.club.entity.*;
import com.swithus.community.club.repository.MeetingMemberRepository;
import com.swithus.community.club.repository.MeetingReplyRepository;
import com.swithus.community.club.repository.MeetingRepository;
import com.swithus.community.club.service.MeetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;
    private final MeetingReplyRepository meetingReplyRepository;

    @Override
    public List<MeetingDTO> getActiveMeetingDTOList(Long clubId, Long meetingCtgrId, LocalDateTime now) {
        List<Object[]> activeMeetingList;
        if (meetingCtgrId == null) {
            activeMeetingList = meetingRepository.getMeetingDTOListByClubIdAndNow(clubId, now);
        } else {
            activeMeetingList = meetingRepository.getMeetingDTOListByClubIdAndCtgrIdAndNow(clubId, meetingCtgrId, now);
        }

        if (ObjectUtils.isEmpty(activeMeetingList)) {
            log.info("일정 데이터 없음");
            // 뭘 보내야 하지?
            return Collections.emptyList();
        } else {
            log.info("일정 데이터 있음");
            return activeMeetingList
                    .stream()
                    .map(objects -> {
                        Meeting meeting = (Meeting) objects[0];
                        Long currentPersonnel = (Long) objects[1];
                        return MeetingDTO.builder()
                                .meetingId(meeting.getId())
                                .ctgrId(meeting.getCtgr().getId())
                                .ctgrName(meeting.getCtgr().getName())
                                .title(meeting.getTitle())
                                .content(meeting.getContent())
                                .mPlace(meeting.getMPlace())
                                .mTime(meeting.getMTime())
                                .mPersonnel(meeting.getMPersonnel())
                                .currentPersonnel(currentPersonnel)
                                .replyCount(0L)
                                .build();
                    })
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void createMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = Meeting.builder()
                .club(Club.builder().id(meetingDTO.getClubId()).build())
                .ctgr(MeetingCtgr.builder().id(meetingDTO.getCtgrId()).build())
                .title(meetingDTO.getTitle())
                .content(meetingDTO.getContent())
                .mTime(meetingDTO.getMTime())
                .mPlace(meetingDTO.getMPlace())
                .mAddr(meetingDTO.getMAddr())
                .mPersonnel(meetingDTO.getMPersonnel())
                .build();

        meetingRepository.save(meeting);
    }

    @Override
    public void updateMeeting(MeetingDTO meetingDTO) {
        Meeting meeting = Meeting.builder()
                .id(meetingDTO.getMeetingId())
                .club(Club.builder().id(meetingDTO.getClubId()).build())
                .ctgr(MeetingCtgr.builder().id(meetingDTO.getCtgrId()).build())
                .title(meetingDTO.getTitle())
                .content(meetingDTO.getContent())
                .mTime(meetingDTO.getMTime())
                .mPlace(meetingDTO.getMPlace())
                .mAddr(meetingDTO.getMAddr())
                .mPersonnel(meetingDTO.getMPersonnel())
                .build();

        meetingRepository.save(meeting);
    }

    @Override
    public MeetingDTO getMeetingDTO(Long meetingId) {
        List<Object[]> resultList = meetingRepository.getMeetingDTOByMeetingId(meetingId);
        if (ObjectUtils.isEmpty(resultList)) {
            return null;
        } else {
            Object[] result = resultList.get(0);
            Meeting meeting = (Meeting) result[0];
            Long currentPersonnel = (Long) result[1];
            Long replyCount = (Long) result[2];

            return MeetingDTO.builder()
                    .meetingId(meeting.getId())
                    .ctgrId(meeting.getCtgr().getId())
                    .ctgrName(meeting.getCtgr().getName())
                    .title(meeting.getTitle())
                    .mTime(meeting.getMTime())
                    .mPlace(meeting.getMPlace())
                    .mAddr(meeting.getMAddr())
                    .content(meeting.getContent())
                    .mPersonnel(meeting.getMPersonnel())
                    .currentPersonnel(currentPersonnel)
                    .replyCount(replyCount)
                    .build();
        }
    }

    @Override
    public Boolean existsMeetingMember(Long meetingId, Long clubMemberId) {
        return meetingMemberRepository.existsByMeetingAndClubMember(meetingId, clubMemberId);
    }

    @Override
    public void insertMeetingMember(Long meetingId, Long clubMemberId) {

        meetingMemberRepository.save(MeetingMember.builder()
                .meeting(Meeting.builder().id(meetingId).build())
                .member(ClubMember.builder().id(clubMemberId).build())
                .build());
    }

    @Override
    @Transactional
    public void deleteMeetingMember(Long meetingId, Long clubMemberId) {
        meetingMemberRepository.deleteMeetingMember(meetingId, clubMemberId);
    }

    @Override
    public void deleteMeeting(Long meetingId) {
        List<MeetingMember> memberList = meetingRepository.getMeetingMemberListByMeetingId(meetingId);
        if (!ObjectUtils.isEmpty(memberList)) {
            memberList.forEach(meetingMember -> meetingMemberRepository.deleteById(meetingMember.getId()));
        }

        List<MeetingReply> replyList = meetingReplyRepository.findByMeeting(Meeting.builder().id(meetingId).build());
        if (!ObjectUtils.isEmpty(replyList)) {
            replyList.forEach(reply -> meetingReplyRepository.deleteById(reply.getId()));
        }

        meetingRepository.deleteById(meetingId);
    }
}
