package com.swithus.community.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardInquiryDTO {

    private Long id;

    private Long ctgrId;

    private String nickname;

    private String title;

    private String content;
}
