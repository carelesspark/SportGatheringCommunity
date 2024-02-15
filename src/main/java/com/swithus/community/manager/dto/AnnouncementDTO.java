package com.swithus.community.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnnouncementDTO {

    private Long id;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String content;

    private String title;

    private int visitCount;

    private String writer;
}
