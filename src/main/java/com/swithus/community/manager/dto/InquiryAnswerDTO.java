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
public class InquiryAnswerDTO {

    private Long id;

    private LocalDateTime regDate;

    private String title;

    private String content;

    private Long inquiryId;

    private boolean isAnswered;
}
