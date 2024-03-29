package com.swithus.community.club.repository.search.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.entity.QClubMember;
import com.swithus.community.club.entity.QGreetings;
import com.swithus.community.club.entity.QGreetingsLike;
import com.swithus.community.club.repository.search.GreetingsSearchRepository;
import com.swithus.community.user.entity.QUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class GreetingsSearchRepositoryImpl extends QuerydslRepositorySupport implements GreetingsSearchRepository {
    // 생성자
    public GreetingsSearchRepositoryImpl() {
        super(Greetings.class);
    }

    @Override
    public Page<Object[]> greetingsSearchPage(Pageable pageable, Long clubId, Long userId) {
        log.info("Club ID: {}, User ID: {}", clubId, userId);

        QGreetings qGreetings = QGreetings.greetings;
        QClubMember qClubMember = QClubMember.clubMember;
        QUser qUser = QUser.user;
        QGreetingsLike qGreetingsLike = QGreetingsLike.greetingsLike;

        // from
        JPQLQuery<Greetings> jpqlQuery = from(qGreetings);
        // join
        jpqlQuery.leftJoin(qClubMember).on(qGreetings.member.eq(qClubMember));
        jpqlQuery.leftJoin(qUser).on(qClubMember.member.eq(qUser));
        jpqlQuery.leftJoin(qGreetingsLike).on(qGreetingsLike.greetings.eq(qGreetings));
        // select
        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery
                .select(qGreetings,
                        qClubMember,
                        qGreetingsLike.countDistinct()
                        , JPAExpressions.select(qGreetingsLike.id.count())
                                .from(qGreetingsLike)
                                .where(qGreetingsLike.member.member.id.eq(userId == null ? 0L : userId)));
        // where
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qClubMember.club.id.eq(clubId));
        if (userId != null) {
            booleanBuilder.and(qClubMember.member.id.eq(userId));
        }
        tupleJPQLQuery.where(booleanBuilder);
        // group by
        tupleJPQLQuery.groupBy(qGreetings, qUser);
        // order by
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();
            String tableName = "greetings";
            // 정렬 기준 정의
            PathBuilder<Greetings> pathBuilder = new PathBuilder<>(Greetings.class, tableName);
            tupleJPQLQuery.orderBy(new OrderSpecifier(direction, pathBuilder.get(property)));
        });
        // 전체 개수 얻기
        long totalCount = tupleJPQLQuery.fetchCount();
        // limit
        tupleJPQLQuery.offset(pageable.getOffset());
        tupleJPQLQuery.limit(pageable.getPageSize());
        // 쿼리문 생성 완료
        List<Tuple> result = tupleJPQLQuery.fetch();

        log.info("Total count: {}", totalCount);

        return new PageImpl<>(result.stream().map(Tuple::toArray).toList(), pageable, totalCount);
    }
}
