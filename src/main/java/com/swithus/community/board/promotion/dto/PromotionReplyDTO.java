package com.swithus.community.board.promotion.dto;

import com.swithus.community.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionReplyDTO {
    private Long rno;
    private String replyContent;
    private User userNick;
    private Long bno;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
