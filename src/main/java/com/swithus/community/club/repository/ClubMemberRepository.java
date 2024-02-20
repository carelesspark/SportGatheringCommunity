package com.swithus.community.club.repository;

import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.repository.search.ClubMemberSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long>, ClubMemberSearchRepository {
}
