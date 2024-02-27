package com.swithus.community.main.dto;

import com.swithus.community.global.dto.ImageDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PopularClubDTO {
    // 링크를 위한 클럽 키
    private Long clubId;
    // 지역 이름
    private String regionName;
    // 종목 이름
    private String sportsName;
    // 클럽명
    private String name;
    // 클럽 헤드라인
    private String headline;

    private Long memberCount;
    private Long meetingCount;
    // 클럽 메인 이미지 리스트
    @Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();
}
