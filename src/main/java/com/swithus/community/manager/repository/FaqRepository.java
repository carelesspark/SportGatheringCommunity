package com.swithus.community.manager.repository;

import com.swithus.community.manager.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<Faq, Long> {
}
