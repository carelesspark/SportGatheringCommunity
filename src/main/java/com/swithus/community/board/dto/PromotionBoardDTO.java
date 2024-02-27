package com.swithus.community.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PromotionBoardDTO {
    private Long id;

    private String title;

    private String content;

    private String sports;

    private String writer;

    private Long visitCount;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replyCount;


}
