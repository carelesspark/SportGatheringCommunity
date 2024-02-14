package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class ClubPostLike extends BaseEntity {

    private Long key;
    // 게시글 키
    private ClubPost post;
    // 누른 사람 키
    private ClubMember member;
}
