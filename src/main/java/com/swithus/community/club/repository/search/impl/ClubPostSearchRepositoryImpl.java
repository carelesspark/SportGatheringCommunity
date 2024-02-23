package com.swithus.community.club.repository.search.impl;

import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.repository.search.ClubPostSearchRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Log4j2
public class ClubPostSearchRepositoryImpl extends QuerydslRepositorySupport implements ClubPostSearchRepository {
    public ClubPostSearchRepositoryImpl() {
        super(ClubPost.class);
    }

    @Override
    public Page<Object[]> clubPostPage(Pageable pageable, Long clubId, Long ctgrId, String type, String keyword) {
        log.info("Club Id: {},  ", clubId);

        return null;
    }
}
