package com.swithus.community.board.promotion.dto;

import com.swithus.community.club.entity.Club;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PromotionDTO {
    private Long promotionId;

    private Long clubId;
    private String clubName;

    private Long writerId;
    private String writerName;

    private String title;
    private String content;

    private int visitCount;

    private Long replyCount;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
