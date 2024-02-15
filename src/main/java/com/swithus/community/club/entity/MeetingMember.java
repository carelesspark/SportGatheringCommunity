package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class MeetingMember extends BaseEntity {

    private Long key;
    // 모임 키
    private Meeting meeting;
    // 멤버 키
    private ClubMember member;
}
