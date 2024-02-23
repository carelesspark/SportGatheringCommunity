package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.repository.search.ClubPostSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubPostRepository extends JpaRepository<ClubPost, Long>, ClubPostSearchRepository {
}
