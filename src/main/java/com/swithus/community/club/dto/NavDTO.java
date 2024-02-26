package com.swithus.community.club.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NavDTO {
    Long clubId;
    // 클럽명 → Club에서 가져옴
    String clubName;
    // 클럽 헤드라인 → Club에서 가져옴
    String clubHeadline;
    // 회원 정보 → ClubMember에서 가져옴 → 들어갈 수 있는 키
    Long clubMemberId;
    String nickname;
    // 1 0 0 0
    // 0 1 0 0
    // 0 0 1 0
    // 0 0 1 1
    boolean isGuest;
    boolean isWaiting;
    boolean isMember;
    boolean isLeader;
}
