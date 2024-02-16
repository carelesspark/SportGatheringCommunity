package com.swithus.community.club.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubSearchRepository {
    Page<Object[]> clubSearchPage(Pageable pageable, Long region, Long sports, String keyword);
}
