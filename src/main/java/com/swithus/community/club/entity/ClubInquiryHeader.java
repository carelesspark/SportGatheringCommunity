package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

import java.time.LocalDateTime;

public class ClubInquiryHeader extends BaseEntity {

    private Long id;
    // 클럽 키
    private Club club;
    // 회원 키


    // 마지막 메시지 작성자
    private byte lastSender;
    // 마지막 메시지 시간
    private LocalDateTime lastSendTime;
}
