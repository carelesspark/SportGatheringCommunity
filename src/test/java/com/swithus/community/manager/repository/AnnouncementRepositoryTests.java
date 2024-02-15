package com.swithus.community.manager.repository;

import com.swithus.community.manager.entity.Announcement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class AnnouncementRepositoryTests {

    @Autowired
    AnnouncementRepository announcementRepository;

    @Test
    public void insertAnnouncement(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Announcement announcement = Announcement.builder()
                    .content("Test Content")
                    .title("Test Title")
                    .visitCount(0)
                    .writer("Manager").build();

            announcementRepository.save(announcement);
        });
    }


}
