package com.swithus.community.global.service.impl;

import com.swithus.community.global.entity.Region;
import com.swithus.community.global.repository.RegionRepository;
import com.swithus.community.global.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Override
    public List<Region> getRegionList() {
        return regionRepository.findAll();
    }
}
