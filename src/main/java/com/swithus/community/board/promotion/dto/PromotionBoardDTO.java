package com.swithus.community.board.promotion.dto;

import com.swithus.community.global.entity.Sports;
import com.swithus.community.user.entity.User;
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
