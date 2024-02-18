package com.swithus.community.manager.dto;

import com.swithus.community.manager.entity.FaqCtgr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqDTO {

    private Long id;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String answer;

    private String question;

    private Long ctgrId;
}
