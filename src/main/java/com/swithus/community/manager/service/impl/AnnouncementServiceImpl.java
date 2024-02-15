package com.swithus.community.manager.service.impl;

import com.swithus.community.manager.repository.AnnouncementRepository;
import com.swithus.community.manager.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;


}
