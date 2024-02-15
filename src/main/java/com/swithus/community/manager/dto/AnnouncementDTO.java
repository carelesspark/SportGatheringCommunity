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

    private LocalDateTime mod_date;

    private LocalDateTime reg_Date;

    private String content;

    private String title;

    private int visit_count;

    private String writer;
}
