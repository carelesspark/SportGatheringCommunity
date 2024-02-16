package com.swithus.community.club.dto;

import com.swithus.community.global.dto.ImageDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClubDTO {
    // 클럽 키
    private Long clubId;
    // 클럽장 키
    private Long leaderId;
    // 지역 키
    private Long regionId;
    // 지역 이름
    private String regionName;
    // 종목 키
    private Long sportsId;
    // 종목 이름
    private String sportsName;

    // 클럽명
    private String name;
    // 클럽 헤드라인
    private String headline;
    // 클럽 소개
    private String introduce;
    // 클럽 인원
    private long personnel;
    // 클럽 랭크
    private int rank;
    // 클럽 포인트
    private int point;
    // 클럽 생성일
    private LocalDateTime regDate;

    // 클럽 메인 이미지 리스트
    @Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();
}
