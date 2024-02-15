package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.SearchPageRequestDTO;
import com.swithus.community.club.dto.SearchPageResultDTO;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.ClubImage;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.entity.Region;
import com.swithus.community.global.entity.Sports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClubSearchService {
    SearchPageResultDTO<ClubDTO, Object[]> getSearchPage(SearchPageRequestDTO requestDTO);

    default Map<String, Object> dtoToEntity(ClubDTO clubDTO) {
        Map<String, Object> clubMap = new HashMap<>();
        Club club = Club.builder()
                .key(clubDTO.getKey())
                //.leader(User.builder().key(clubDTO.getLeaderKey()).build())
                .region(Region.builder().key(clubDTO.getRegionKey()).build())
                .sports(Sports.builder().key(clubDTO.getSportsKey()).build())
                .name(clubDTO.getName())
                .introduce(clubDTO.getIntroduce())
                .rank(clubDTO.getRank())
                .point(clubDTO.getPoint())
                .build();
        clubMap.put("club", club);

        List<ImageDTO> imageDTOList = clubDTO.getImageDTOList();
        if (imageDTOList != null && !imageDTOList.isEmpty()) {
            List<ClubImage> imageList = imageDTOList.stream()
                    .map(imageDTO -> ClubImage.builder()
                            .path(imageDTO.getPath())
                            .name(imageDTO.getName())
                            .uuid(imageDTO.getUuid())
                            .club(club)
                            .build())
                    .toList();
            clubMap.put("imageList", imageList);
        }

        return clubMap;
    }

    default ClubDTO entityToDTO(Club club, List<ClubImage> imageList, Long personnel) {
        List<ImageDTO> imageDTOList = imageList.stream()
                .map(image -> ImageDTO.builder()
                        .path(image.getPath())
                        .name(image.getName())
                        .uuid(image.getUuid())
                        .build())
                .toList();

        return ClubDTO.builder()
                .key(club.getKey())
                //.leaderKey(club.getLeader())
                .regionKey(club.getRegion().getKey())
                .regionName(club.getRegion().getName())
                .sportsKey(club.getSports().getKey())
                .sportsName(club.getSports().getName())
                .name(club.getName())
                .introduce(club.getIntroduce())
                .personnel(personnel)
                .rank(club.getRank())
                .point(club.getPoint())
                .regDate(club.getRegDate())
                .imageDTOList(imageDTOList)
                .build();
    }
}
