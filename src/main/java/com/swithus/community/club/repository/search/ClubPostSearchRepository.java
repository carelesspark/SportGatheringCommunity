package com.swithus.community.club.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubPostSearchRepository {
    Page<Object[]> clubPostPage(Pageable pageable,
                                Long clubId,
                                Long ctgrId,
                                String type,
                                String keyword);
}
