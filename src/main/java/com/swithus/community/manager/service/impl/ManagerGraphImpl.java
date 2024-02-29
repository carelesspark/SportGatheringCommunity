package com.swithus.community.manager.service.impl;

import com.swithus.community.club.repository.ClubRepository;
import com.swithus.community.manager.repository.UserRepository;
import com.swithus.community.manager.service.ManagerGraphService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ManagerGraphImpl implements ManagerGraphService {

    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    @Override
    public List<Long> countUserGraph() {

        List<Long> result = new ArrayList<>();

        Long today = userRepository.countByNow();
        Long one_day_ago = userRepository.countByNowInterval1Day();
        Long two_days_ago = userRepository.countByNowInterval2Day();
        Long three_days_ago = userRepository.countByNowInterval3Day();
        Long four_days_ago = userRepository.countByNowInterval4Day();


        result.add(four_days_ago);
        result.add(three_days_ago);
        result.add(two_days_ago);
        result.add(one_day_ago);
        result.add(today);

        log.info(result);

        return result;
    }

    @Override
    public List<String> getDatesList() {
        List<String> dates = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        LocalDate dayBeforeYesterday = today.minusDays(2);
        LocalDate threeDaysAgo = today.minusDays(3);
        LocalDate fourDaysAgo = today.minusDays(4);

        dates.add(formatDate(fourDaysAgo));
        dates.add(formatDate(threeDaysAgo));
        dates.add(formatDate(dayBeforeYesterday));
        dates.add(formatDate(yesterday));
        dates.add(formatDate(today));

        log.info(dates);

        return dates;
    }

    private String formatDate(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern(("yy-MM-dd")));
    }

    @Override
    public List<Long> countGatheringGraph() {

        List<Long> countCtgr = new ArrayList<>();

        Long basketball = clubRepository.countBySportsWhichNameBasketball();
        Long soccer = clubRepository.countBySportsWhichNameSoccer();
        Long baseball = clubRepository.countBySportsWhichNameBaseball();
        Long table_tennis = clubRepository.countBySportsWhichNameTable_Tennis();
        Long badminton = clubRepository.countBySportsWhichNameBadminton();

        countCtgr.add(soccer);
        countCtgr.add(baseball);
        countCtgr.add(basketball);
        countCtgr.add(table_tennis);
        countCtgr.add(badminton);

        return countCtgr;
    }
}
