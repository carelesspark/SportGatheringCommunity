package com.swithus.community.club.repository;

import com.swithus.community.club.entity.Club;
import com.swithus.community.club.repository.search.ClubSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long>, ClubSearchRepository {

}
