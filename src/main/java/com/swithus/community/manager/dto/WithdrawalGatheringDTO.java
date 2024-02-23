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
public class WithdrawalGatheringDTO {

    private Long id;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String deleteReason;

    private String leaderName;

    private String clubName;

    private Long clubId;
}
