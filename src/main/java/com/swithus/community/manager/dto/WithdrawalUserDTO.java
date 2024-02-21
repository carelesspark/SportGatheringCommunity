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
public class WithdrawalUserDTO {

    private Long id;

    private String userId;

    private String email;

    private String deleteReason;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private Long authUserId;

    private Long authUserDetailId;
}
