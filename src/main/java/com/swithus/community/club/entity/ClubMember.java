package com.swithus.community.club.entity;

import com.swithus.community.global.entity.BaseEntity;
import com.swithus.community.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@ToString
public class ClubMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 클럽 키
    @ManyToOne(fetch = FetchType.LAZY)
    private Club club;
    // 회원 키
    @ManyToOne(fetch = FetchType.LAZY)
    private User member;

    // 랭크(0은 가입 신청을 넣은 유저)
    private int rank;
    // 탈퇴여부 확인(1은 활동 회원, 0은 탈퇴 회원)
    private byte isActive;
    // 블랙리스트 여부
    private byte isBlacklist;
    // 블랙리스트 사유
    private String blacklistReason;
}
