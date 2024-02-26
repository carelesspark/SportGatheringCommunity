package com.swithus.community.club.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClubPostReplyDTO {
    private Long replyId;
    private Long postId;
    private Long writerId;
    private String nickname;
    private String comment;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
