package com.swithus.community.club.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GreetingsSearchRepository {
    Page<Object[]> greetingsSearchPage(Pageable pageable, Long clubId, Long userId);
}
