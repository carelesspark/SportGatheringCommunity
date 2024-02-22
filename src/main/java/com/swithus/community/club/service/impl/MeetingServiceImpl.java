package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.MeetingDTO;
import com.swithus.community.club.service.MeetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    @Override
    public List<MeetingDTO> getActiveMeetingDTOList(Long clubId, LocalDateTime now) {
        return null;
    }
}
