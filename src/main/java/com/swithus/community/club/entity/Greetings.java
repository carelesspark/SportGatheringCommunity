package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class Greetings extends BaseEntity {

    private Long key;
    // 멤버 키
    private ClubMember member;
    
    // 내용
    private String content;
}
