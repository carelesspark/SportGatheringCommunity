package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class ClubPostImage extends BaseEntity {

    private Long key;
    // 게시글 키
    private ClubPost post;

    // 이름
    private String name;
    // 경로
    private String path;
    // uuid
    private String uuid;
}
