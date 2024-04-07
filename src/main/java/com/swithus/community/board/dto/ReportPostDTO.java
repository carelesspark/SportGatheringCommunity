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

    private String reason;

    private String writer;

    private Long postId;
}
