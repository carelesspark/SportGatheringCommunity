package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.global.entity.Region;
import com.swithus.community.global.entity.Sports;

public class Club extends BaseEntity {

    private Long key;

    // 클럽장 키

    // 종목 키
    private Sports sports;
    // 지역 키
    private Region region;

    // 클럽명
    private String name;
    // 클럽 소개
    private String introduce;
    // 클럽 인원
    private int personnel;
    // 클럽 랭크
    private int rank;
    // 클럽 포인트
    private int point;
}
