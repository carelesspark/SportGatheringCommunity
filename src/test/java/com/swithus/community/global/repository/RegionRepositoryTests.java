package com.swithus.community.global.repository;

import com.swithus.community.global.entity.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class RegionRepositoryTests {
    @Autowired
    private RegionRepository regionRepository;

    @Test
    void insertRegionTest() {
        // 수행 완료하였습니다.
        List<String> regionList = new ArrayList<String>();
        regionList.add("서울");
        regionList.add("경기");
        regionList.add("인천");
        regionList.add("강원");

        regionList.add("충남");
        regionList.add("대전");
        regionList.add("충북");
        regionList.add("세종");

        regionList.add("부산");
        regionList.add("울산");
        regionList.add("대구");
        regionList.add("경북");
        regionList.add("경남");

        regionList.add("전남");
        regionList.add("광주");
        regionList.add("전북");

        regionList.add("제주");

        IntStream.rangeClosed(0, regionList.size() - 1).forEach(i -> {
            Region region = Region.builder().name(regionList.get(i)).build();
            regionRepository.save(region);
        });
    }
}
