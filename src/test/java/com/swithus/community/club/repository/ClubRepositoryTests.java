package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.Meeting;
import com.swithus.community.club.entity.MeetingCtgr;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class ClubRepositoryTests {
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    MeetingCtgrRepository meetingCtgrRepository;

    @Test
    void insetMeeting() {
        LocalDateTime weekLater = LocalDateTime.now().plusDays(7);

        Meeting meeting = Meeting.builder()
                .title("모임명4")
                .content("모임 설명4")
                .mTime(weekLater)
                .mPlace("서울특별시")
                .mPersonnel(10)
                .club(Club.builder().id(12L).build())
                .ctgr(MeetingCtgr.builder().id(4L).build())
                .build();

        meetingRepository.save(meeting);
    }

    @Test
    void insertMeetingCtgr() {
        // 사용했음
        List<String> ctgrList = new ArrayList<>();
        ctgrList.add("정기모임");
        ctgrList.add("송년회");
        ctgrList.add("번개모임");
        ctgrList.add("회식");

        IntStream.rangeClosed(0, ctgrList.size() - 1).forEach(i -> {
            MeetingCtgr ctgr = MeetingCtgr.builder()
                    .name(ctgrList.get(i)).build();
            meetingCtgrRepository.save(ctgr);
        });

    }


}
