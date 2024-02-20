package com.swithus.community.club.dto;

import com.swithus.community.global.dto.ImageDTO;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClubMemberDTO {
    // 클럽회원 키
    private Long clubMemberId;
    // 클럽 키
    private Long clubId;
    // 회원 키
    private Long memberId;
    // 회원 이름
    private String memberName;

    // 랭크
    private int rank;
    // 탈퇴여부 확인(1은 활동 회원, 0은 탈퇴 회원)
    private byte isActive;
    // 블랙리스트 여부
    private byte isBlacklist;
    // 블랙리스트 사유
    private String blacklistReason;
    // 가입일
    private LocalDateTime regDate;
    // User 사진
    private ImageDTO imageDTO;
}
