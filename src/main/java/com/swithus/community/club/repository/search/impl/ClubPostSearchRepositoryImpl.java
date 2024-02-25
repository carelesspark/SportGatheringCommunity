package com.swithus.community.club.repository.search.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.swithus.community.club.entity.*;
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
        // booleanBuilder.and(qPost.)

        return null;
    }
}
