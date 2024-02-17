package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.dto.page.SearchPageResultDTO;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.ClubImage;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.entity.Region;
import com.swithus.community.global.entity.Sports;
import com.swithus.community.user.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClubService {
    // 클럽 검색 페이지 리스트 반환
    SearchPageResultDTO<ClubDTO, Object[]> getSearchPage(SearchPageRequestDTO requestDTO);

    // 새 클럽 생성
    Long create(ClubDTO clubDTO);

    // ClubDTO 반환
    ClubDTO getClub(Long clubId);

    default Map<String, Object> clubDTOToEntity(ClubDTO clubDTO) {
        Map<String, Object> clubMap = new HashMap<>();

        Club club = Club.builder()
                .id(clubDTO.getClubId())
                .leader(User.builder().id(clubDTO.getLeaderId()).build())
                .region(Region.builder().id(clubDTO.getRegionId()).build())
                .sports(Sports.builder().id(clubDTO.getSportsId()).build())
                .name(clubDTO.getName())
                .headline(clubDTO.getHeadline())
                .introduce(clubDTO.getIntroduce())
                .rank(clubDTO.getRank())
                .point(clubDTO.getPoint())
                .build();
        clubMap.put("club", club);

        List<ImageDTO> imageDTOList = clubDTO.getImageDTOList();

        if (imageDTOList != null && !imageDTOList.isEmpty()) {
            List<ClubImage> imageList = imageDTOList.stream()
                    .map(imageDTO -> ClubImage.builder()
                            .club(club)
                            .path(imageDTO.getPath())
                            .name(imageDTO.getName())
                            .uuid(imageDTO.getUuid())
                            .build())
                    .toList();
            clubMap.put("imageList", imageList);
        }

        return clubMap;
    }

    default ClubDTO entityToClubDTO(Club club, List<ClubImage> imageList, Long personnel) {
        List<ImageDTO> imageDTOList = imageList.stream()
                .map(image -> ImageDTO.builder()
                        .path(image.getPath())
                        .name(image.getName())
                        .uuid(image.getUuid())
                        .build())
                .toList();

        return ClubDTO.builder()
                .clubId(club.getId())
                .leaderId(club.getLeader().getId())
                .regionId(club.getRegion().getId())
                .regionName(club.getRegion().getName())
                .sportsId(club.getSports().getId())
                .sportsName(club.getSports().getName())
                .name(club.getName())
                .headline(club.getHeadline())
                .introduce(club.getIntroduce())
                .personnel(personnel)
                .rank(club.getRank())
                .point(club.getPoint())
                .regDate(club.getRegDate())
                .imageDTOList(imageDTOList)
                .build();
    }

}
