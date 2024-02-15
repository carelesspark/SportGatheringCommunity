package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class ClubInquiry extends BaseEntity {

    private Long key;
    // 문의 헤더 키
    private ClubInquiryHeader header;

    // 내용
    private String comment;
    // 작성자
    private byte writer;
    // 읽기 여부
    private byte has_read;
}
