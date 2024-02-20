package com.swithus.community.club.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubMemberSearchRepository {
    Page<Object[]> memberPage(Pageable pageable, Long clubId);
}
