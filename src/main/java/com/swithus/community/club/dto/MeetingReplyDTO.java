package com.swithus.community.club.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeetingReplyDTO {
    private Long meetingReplyId;
    private Long meetingId;
    private Long memberId;
    private Long userId;
    private String memberName;
    private String memberNickname;
    private String comment;
    private LocalDateTime regDate;
}
