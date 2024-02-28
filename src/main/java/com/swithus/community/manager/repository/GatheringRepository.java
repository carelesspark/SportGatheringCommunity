package com.swithus.community.manager.repository;

import com.swithus.community.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GatheringRepository extends JpaRepository<Club, Long>, QuerydslPredicateExecutor<Club> {
    Long countBy();


}
