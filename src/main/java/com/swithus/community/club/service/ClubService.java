package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.NavDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.ClubImage;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.global.entity.Region;
import com.swithus.community.global.entity.Sports;
import com.swithus.community.user.entity.User;
import org.springframework.util.ObjectUtils;

import java.util.*;

public interface ClubService {
    // 클럽 검색 페이지 리스트 반환
    PageResultDTO<ClubDTO, Object[]> getSearchPage(SearchPageRequestDTO requestDTO);

    // 새 클럽 생성
    Long create(ClubDTO clubDTO);

    // ClubDTO 반환
    ClubDTO getClub(Long clubId);

    NavDTO getNavDTO(Long clubId, Long clubMemberId);

    Long registerClub(Long clubId, Long userId);

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

        List<ClubImage> imageList;
        if (imageDTOList != null && !imageDTOList.isEmpty()) {
            imageList = imageDTOList.stream()
                    .map(imageDTO -> ClubImage.builder()
                            .club(club)
                            .path(imageDTO.getPath())
                            .name(imageDTO.getName())
                            .uuid(imageDTO.getUuid())
                            .build())
                    .toList();
        } else {
            imageList = new ArrayList<>();
        }
        clubMap.put("imageList", imageList);

        return clubMap;
    }

    default ClubDTO entityToClubDTO(Club club, List<ClubImage> imageList, Long personnel) {
        List<ImageDTO> imageDTOList = new ArrayList<>();

        if (ObjectUtils.isEmpty(imageList)) {
            ImageDTO imageDTO = ImageDTO.builder()
                    .uuid("uuid")
                    .name(club.getSports().getName() + ".jpg")
                    .path("club/main")
                    .build();

            imageDTOList.add(imageDTO);
        } else {
            imageDTOList = imageList.stream()
                    .map(image -> ImageDTO.builder()
                            .path(image.getPath())
                            .name(image.getName())
                            .uuid(image.getUuid())
                            .build())
                    .toList();
        }

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
