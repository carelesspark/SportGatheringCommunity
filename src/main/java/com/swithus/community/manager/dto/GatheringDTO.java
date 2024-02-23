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
public class GatheringDTO {

    private Long id;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private String clubName;

    private String clubLeader;

    private int memberCount;

    private String ctgr;





}
