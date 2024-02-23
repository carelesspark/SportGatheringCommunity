package com.swithus.community.club.service.impl;

import com.swithus.community.club.entity.MeetingCtgr;
import com.swithus.community.club.repository.MeetingCtgrRepository;
import com.swithus.community.club.service.MeetingCtgrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MeetingCtgrServiceImpl implements MeetingCtgrService {
    private final MeetingCtgrRepository meetingCtgrRepository;

    @Override
    public List<MeetingCtgr> getCtgrList() {
        return meetingCtgrRepository.findAll();
    }
}
