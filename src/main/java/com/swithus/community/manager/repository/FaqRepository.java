package com.swithus.community.manager.repository;

import com.swithus.community.manager.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FaqRepository extends JpaRepository<Faq, Long>, QuerydslPredicateExecutor<Faq> {
}
