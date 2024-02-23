package com.swithus.community.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDTO {

    private Long id;

    private String title;

    private String writer;

    private LocalDateTime regDate;

    private boolean isAnswered;

    private String content;

    private String ctgr;

    private Long answerId;
}
