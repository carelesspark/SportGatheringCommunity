package com.swithus.community.club.repository.search;

import com.swithus.community.club.entity.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ClubSearchRepositoryImpl extends QuerydslRepositorySupport implements ClubSearchRepository {
    // 생성자
    public ClubSearchRepositoryImpl() {
        super(Club.class);
    }

    @Override
    public Page<Object[]> clubSearchPage(Pageable pageable, int region, int sports, String keyword) {

        return null;
    }
}
