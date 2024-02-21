package com.swithus.community.club.repository.search.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.entity.QClubMember;
import com.swithus.community.club.repository.search.ClubMemberSearchRepository;
import com.swithus.community.user.entity.QUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ClubMemberSearchRepositoryImpl extends QuerydslRepositorySupport implements ClubMemberSearchRepository {
    public ClubMemberSearchRepositoryImpl() {
        super(Greetings.class);
    }

    @Override
    public Page<Object[]> memberPage(Pageable pageable, Long clubId) {
        QClubMember qClubMember = QClubMember.clubMember;
        QUser qUser = QUser.user;

        // from
        JPQLQuery<ClubMember> jpqlQuery = from(qClubMember);
        // join
        jpqlQuery.leftJoin(qUser).on(qClubMember.member.eq(qUser));
        // select
        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(qClubMember, qUser);
        // where
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qClubMember.club.id.eq(clubId));
        tupleJPQLQuery.where(booleanBuilder);
        // group by
        // order by
        Sort sort = pageable.getSort();
        sort.forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String property = order.getProperty();
            String tableName = "member";
            PathBuilder<ClubMember> pathBuilder = new PathBuilder<>(ClubMember.class, tableName);
            tupleJPQLQuery.orderBy(new OrderSpecifier(direction, pathBuilder.get(property)));
        });
        // 전체 개수
        long totalCount = tupleJPQLQuery.fetchCount();
        // limit
        tupleJPQLQuery.offset(pageable.getOffset());
        tupleJPQLQuery.limit(pageable.getPageSize());
        // 완료
        List<Tuple> result = tupleJPQLQuery.fetch();

        return new PageImpl<>(result.stream().map(Tuple::toArray).toList(), pageable, totalCount);
    }
}
