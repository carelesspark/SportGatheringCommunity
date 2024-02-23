package com.swithus.community.club.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeetingDTO {
    // 모임 아이디
    private Long meetingId;
    // 모임 종류 아이디
    private Long ctgrId;
    // 모임 종류 이름
    private String ctgrName;
    // 모임 제목
    private String title;
    // 모임 시간
    private LocalDateTime mTime;
    // 모임 장소
    private String mPlace;
    // 모임 설명
    private String content;
    // 한도 인원
    private int mPersonnel;
    //  현재 인원
    private Long currentPersonnel;
    // 댓글 개수
    private Long replyCount;
}
