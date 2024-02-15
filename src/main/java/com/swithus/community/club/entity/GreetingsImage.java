package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class GreetingsImage extends BaseEntity {

    private Long id;
    // 가입인사 키
    private Greetings greetings;

    // 이름
    private String name;
    // 경로
    private String path;
    // uuid
    private String uuid;
}
