package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class ClubPostReply extends BaseEntity {

    private Long id;
    // 게시글 키
    private ClubPost post;
    // 작성자 키
    private ClubMember writer;

    // 내용
    private String comment;
}
