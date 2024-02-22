package com.swithus.community.club.service;

import com.swithus.community.club.dto.MeetingDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingService {
    List<MeetingDTO> getActiveMeetingDTOList(Long clubId, LocalDateTime now);
}
