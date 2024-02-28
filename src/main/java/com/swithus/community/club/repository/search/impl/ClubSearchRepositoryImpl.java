package com.swithus.community.club.repository.search.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.QClub;
import com.swithus.community.club.entity.QClubImage;
import com.swithus.community.club.entity.QClubMember;
import com.swithus.community.club.repository.search.ClubSearchRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class ClubSearchRepositoryImpl extends QuerydslRepositorySupport implements ClubSearchRepository {
    // 생성자
    public ClubSearchRepositoryImpl() {
        super(Club.class);
    }

    @Override
    public Page<Object[]> clubSearchPage(Pageable pageable, Long regionId, Long sportsId, String keyword) {
        log.info("RegionId: {}, SportsId: {}, Keyword: {}", regionId, sportsId, keyword);

        QClub club = QClub.club;
        QClubImage clubImage = QClubImage.clubImage;
        QClubMember clubMember = QClubMember.clubMember;

        // from
        JPQLQuery<Club> jpqlQuery = from(club);
        // join
        jpqlQuery.leftJoin(clubMember).on(clubMember.club.eq(club));
        jpqlQuery.leftJoin(clubImage).on(clubImage.club.eq(club));
        // select c, ci, count(distinct cm)
        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(club,
                clubImage,
                clubMember.countDistinct());
        // where
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(club.id.gt(0L));
        // 지역, 종목, 글을 전달 받았을 때
        if (regionId != null) {
            booleanBuilder.and(club.region.id.eq(regionId));
        }
        if (sportsId != null) {
            booleanBuilder.and(club.sports.id.eq(sportsId));
        }
        if (keyword != null && !keyword.isEmpty()) {
            booleanBuilder.and(club.name.contains(keyword));
        }
        tupleJPQLQuery.where(booleanBuilder);
        // group by
        tupleJPQLQuery.groupBy(club);
        // order by
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();
            // 정렬 기준 정의
            PathBuilder<Club> pathBuilder = new PathBuilder<>(Club.class, "club");
            tupleJPQLQuery.orderBy(new OrderSpecifier(direction, pathBuilder.get(property)));
        });
        // limit
        tupleJPQLQuery.offset(pageable.getOffset());
        tupleJPQLQuery.limit(pageable.getPageSize());
        // 쿼리문 생성 완료
        List<Tuple> result = tupleJPQLQuery.fetch();
        // Page interface 생성
        // 전체 개수를 얻기 위한 코드
        long total = tupleJPQLQuery.fetchCount();
        log.info("Total count: {}", total);
        // PageImpl 객체 → 페이지 상속받을 객체
        return new PageImpl<>(result.stream().map(Tuple::toArray).toList(), pageable, total);
    }
}
