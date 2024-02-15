package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class ClubPost extends BaseEntity {

    private Long key;
    // 작성자 키
    private ClubMember writer;

    // 제목
    private String title;
    // 내용
    private String content;
    // 조회수
    private int visit_count;
}
