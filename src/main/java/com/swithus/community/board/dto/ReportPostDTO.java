package com.swithus.community.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportPostDTO {

    private Long id;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String nickname;

    private Long ctgrId;

    private String ctgrType;

    private String reason;

    private String postWriter;

    private boolean isSolved;

    private boolean isSuitabled;

    private Long postId;

    private String postContent;
}
