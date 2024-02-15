package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

import java.time.LocalDateTime;

public class Meeting extends BaseEntity {

    private Long key;
    // 클럽 키
    private Club club;
    // 카테고리 키
    private MeetingCtgr ctgr;

    // 날짜
    private LocalDateTime when;
    // 위치
    private String where;
    // 설명
    private String what;
    // 인원
    private int who;
}
