package com.swithus.community.manager.repository;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findTop4ByOrderByRegDateDesc();
}
