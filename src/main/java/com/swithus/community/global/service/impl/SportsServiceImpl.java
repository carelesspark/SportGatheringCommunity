package com.swithus.community.global.service.impl;

import com.swithus.community.global.entity.Sports;
import com.swithus.community.global.repository.SportsRepository;
import com.swithus.community.global.service.SportsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class SportsServiceImpl implements SportsService {
    private final SportsRepository sportsRepository;

    @Override
    public List<Sports> getSportsList() {
        return sportsRepository.findAll();
    }
}
