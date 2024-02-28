package com.swithus.community.board.dto;

import com.swithus.community.club.entity.Club;
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

    private String clubCtgr;

    private String clubName;

    private Long clubId;

    private Long userId;

    private String writer;

    private int visitCount;

    private String nickname;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
