package com.swithus.community.club.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeetingDTO {
    private Long MeetingId;
    private Long MeetingCtgrId;

    private String MeetingCtgrName;

    private LocalDateTime when;
    private String where;
    private String what;
    private int who;

    private Long currentPersonnel;
}
