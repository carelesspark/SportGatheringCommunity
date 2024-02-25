package com.swithus.community.club.repository.search.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.swithus.community.club.entity.*;
import com.swithus.community.club.repository.search.ClubPostSearchRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class ClubPostSearchRepositoryImpl extends QuerydslRepositorySupport implements ClubPostSearchRepository {
    public ClubPostSearchRepositoryImpl() {
        super(ClubPost.class);
    }

    @Override
    public Page<Object[]> clubPostPage(Pageable pageable, Long clubId, Long ctgrId, String type, String keyword) {
        log.info("clubId: {}, ctgrId: {}, type: {}, keyword: {}", clubId, ctgrId, type, keyword);

        QClubPost qPost = QClubPost.clubPost;
        QClubMember qMember = QClubMember.clubMember;
        QClubPostImage qImage = QClubPostImage.clubPostImage;
        QClubPostReply qReply = QClubPostReply.clubPostReply;
        QClubPostLike qLike = QClubPostLike.clubPostLike;

        // from
        JPQLQuery<ClubPost> jpqlQuery = from(qPost);
        // join
        jpqlQuery.leftJoin(qMember).on(qPost.writer.eq(qMember));
        jpqlQuery.leftJoin(qImage).on(qImage.post.eq(qPost));
        jpqlQuery.leftJoin(qReply).on(qReply.post.eq(qPost));
        jpqlQuery.leftJoin(qLike).on(qLike.post.eq(qPost));
        // select
        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery
                .select(qPost,
                        qReply.countDistinct(),
                        qLike.countDistinct(),
                        qImage);
        // where
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qMember.club.id.eq(clubId));
        if (ctgrId != null) {
            booleanBuilder.and(qPost.ctgr.id.eq(ctgrId));
        }
        if (type != null) {
            String[] typeArray = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();
            for (String str : typeArray) {
                switch (str) {
                    case "t":
                        // 제목을 선택했을 때
                        conditionBuilder.or(qPost.title.contains(keyword));
                        break;
                    case "w":
                        // 글쓴이를 선택했을 때
                        conditionBuilder.or(qPost.writer.nickname.contains(keyword));
                        break;
                    case "c":
                        // 내용을 선택했을 때
                        conditionBuilder.or(qPost.content.contains(keyword));
                        break;
                    default:
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tupleJPQLQuery.where(booleanBuilder);
        // group by
        tupleJPQLQuery.groupBy(qPost, qImage);
        // order by
        Sort sort = pageable.getSort();
        sort.stream().forEach(order -> {
            Order direction = order.isDescending() ? Order.DESC : Order.ASC;
            String property = order.getProperty();
            // 정렬 기준 정의
            PathBuilder<ClubPost> pathBuilder = new PathBuilder<>(ClubPost.class, "ClubPost");
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
