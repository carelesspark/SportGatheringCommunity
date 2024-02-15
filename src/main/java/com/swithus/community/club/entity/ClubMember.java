package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;

public class ClubMember extends BaseEntity {

    private Long key;
    // 클럽 키
    private Club club;
    // 회원 키


    // 랭크(0은 가입 신청을 넣은 유저)
    private int rank;
    // 탈퇴여부 확인(1은 활동 회원, 0은 탈퇴 회원)
    private byte isActive;
    // 블랙리스트 여부
    private byte isBlacklist;
    // 블랙리스트 사유
    private String blacklistReason;
}
