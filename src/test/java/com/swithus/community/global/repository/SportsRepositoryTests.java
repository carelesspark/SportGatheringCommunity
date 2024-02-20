package com.swithus.community.global.repository;

import com.swithus.community.global.entity.Sports;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class SportsRepositoryTests {
    @Autowired
    private SportsRepository sportsRepository;

    @Test
    void insertSportsTest() {
        // 수행 완료하였습니다.
        List<String> sportsList = new ArrayList<String>();
        sportsList.add("축구");
        sportsList.add("농구");
        sportsList.add("야구");
        sportsList.add("배드민턴");
        sportsList.add("탁구");

        IntStream.rangeClosed(0, sportsList.size()-1).forEach(i->{
            Sports sports = Sports.builder().name(sportsList.get(i)).build();
            sportsRepository.save(sports);
        });

        
    }

}
