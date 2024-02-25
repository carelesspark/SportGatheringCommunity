package com.swithus.community.club.repository;

import com.swithus.community.club.entity.*;
import com.swithus.community.global.entity.Region;
import com.swithus.community.global.entity.Sports;
import com.swithus.community.user.entity.User;
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
    ClubRepository clubRepository;
    @Autowired
    ClubMemberRepository clubMemberRepository;
    @Autowired
    MeetingCtgrRepository meetingCtgrRepository;
    @Autowired
    ClubPostCtgrRepository clubPostCtgrRepository;
    @Autowired
    GreetingsRepository greetingsRepository;
    @Autowired
    GreetingsLikeRepository greetingsLikeRepository;
    @Autowired
    MeetingRepository meetingRepository;
    @Autowired
    MeetingMemberRepository meetingMemberRepository;


    @Test
    void insertMeetingCtgrTest() {
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

    @Test
    void insertClubPostCtgrTest() {
        // 추가함
        List<String> ctgrList = new ArrayList<>();
        ctgrList.add("모임 후기");
        ctgrList.add("자유 글");

        IntStream.rangeClosed(0, ctgrList.size() - 1).forEach(i -> {
            ClubPostCtgr ctgr = ClubPostCtgr.builder()
                    .name(ctgrList.get(i)).build();
            clubPostCtgrRepository.save(ctgr);
        });
    }

    @Test
    void insertClubTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Club club = Club.builder()
                    .leader(User.builder().id((long) i).build())
                    .region(Region.builder().id((long) (i % 2 + 1)).build())
                    .sports(Sports.builder().id((long) (i % 5 + 1)).build())
                    .name("Club " + i)
                    .headline("Headline " + i)
                    .introduce("Introduce " + i)
                    .rank(0)
                    .point(0)
                    .build();

            clubRepository.save(club);

            ClubMember clubMember = ClubMember.builder()
                    .club(Club.builder().id(club.getId()).build())
                    .member(User.builder().id((long) i).build())
                    .nickname("Leader")
                    .rank(100)
                    .isActive((byte) 1)
                    .build();

            clubMemberRepository.save(clubMember);
        });
    }

    @Test
    void insertClubMemberTest() {
        Long clubId = 101L;

        IntStream.rangeClosed(1, 99).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .club(Club.builder().id(clubId).build())
                    .member(User.builder().id((long) i).build())
                    .nickname("nick" + i)
                    .rank(1)
                    .isActive((byte) 1)
                    .build();

            clubMemberRepository.save(clubMember);
        });
    }

    @Test
    void insertGreetingsTest() {
        Long clubId = 101L;

        IntStream.rangeClosed(102, 200).forEach(i -> {
            Greetings greetings = Greetings.builder()
                    .member(ClubMember.builder().id((long) i).build())
                    .content("반갑습니다. 즐겁게 공 차요~")
                    .build();

            greetingsRepository.save(greetings);
        });
    }

    @Test
    void insertGreetingsLike1Test() {
        Long greetingsId = 1L;

        IntStream.rangeClosed(102, 200).forEach(i -> {
            GreetingsLike like = GreetingsLike.builder()
                    .greetings(Greetings.builder().id(greetingsId).build())
                    .member(ClubMember.builder().id((long) i).build())
                    .build();

            greetingsLikeRepository.save(like);
        });
    }

    @Test
    void insertGreetingsLike2Test() {
        Long clubMemberId = 101L;

        IntStream.rangeClosed(2, 100).forEach(i -> {
            GreetingsLike like = GreetingsLike.builder()
                    .greetings(Greetings.builder().id((long) i).build())
                    .member(ClubMember.builder().id(clubMemberId).build())
                    .build();

            greetingsLikeRepository.save(like);
        });
    }

    @Test
    void insertMeetingTest() {
        Long cludId = 101L;
        LocalDateTime weekLater = LocalDateTime.now().plusDays(7);

        IntStream.rangeClosed(1, 4).forEach(i -> {
            Meeting meeting = Meeting.builder()
                    .club(Club.builder().id(cludId).build())
                    .ctgr(MeetingCtgr.builder().id((long) i).build())
                    .title("모임 " + i)
                    .content("모임 내용 " + i)
                    .mTime(weekLater)
                    .mPlace("중동고등학교")
                    .mAddr("서울 강남구 일원로 7")
                    .mPersonnel(10)
                    .build();

            meetingRepository.save(meeting);
        });
    }

    @Test
    void insertMeetingMemberTest() {
        Long meetingId = 1L;
        IntStream.rangeClosed(102, 108).forEach(i -> {
            MeetingMember meetingMember = MeetingMember.builder()
                    .meeting(Meeting.builder().id(meetingId).build())
                    .member(ClubMember.builder().id((long) i).build())
                    .build();

            meetingMemberRepository.save(meetingMember);
        });
    }

}
