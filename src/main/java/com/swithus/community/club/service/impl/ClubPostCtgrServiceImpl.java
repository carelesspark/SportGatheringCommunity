package com.swithus.community.club.service.impl;

import com.swithus.community.club.entity.ClubPostCtgr;
import com.swithus.community.club.repository.ClubPostCtgrRepository;
import com.swithus.community.club.service.ClubPostCtgrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubPostCtgrServiceImpl implements ClubPostCtgrService {
    private final ClubPostCtgrRepository ctgrRepository;

    @Override
    public List<ClubPostCtgr> getCtgrList() {
        return ctgrRepository.findAll();
    }
}
