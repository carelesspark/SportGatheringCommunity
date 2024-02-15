package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class GreetingsLike extends BaseEntity {

    private Long id;
    // 가입인사 키
    private Greetings greetings;
    // 누른 사람 키
    private ClubMember member;
}
