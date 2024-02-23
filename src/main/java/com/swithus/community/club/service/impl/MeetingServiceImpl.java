package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.MeetingDTO;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.entity.Meeting;
import com.swithus.community.club.entity.MeetingMember;
import com.swithus.community.club.repository.MeetingMemberRepository;
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
}