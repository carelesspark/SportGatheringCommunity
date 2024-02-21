package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.NavDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.dto.page.SearchPageResultDTO;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.ClubImage;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.repository.ClubImageRepository;
import com.swithus.community.club.repository.ClubMemberRepository;
import com.swithus.community.club.repository.ClubRepository;
import com.swithus.community.club.service.ClubService;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubImageRepository clubImageRepository;
    private final ClubMemberRepository clubMemberRepository;

    @Override
    public SearchPageResultDTO<ClubDTO, Object[]> getSearchPage(SearchPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Long regionId = requestDTO.getRegionId();
        Long sportsId = requestDTO.getSportsId();
        String keyword = requestDTO.getKeyword();

        // 조건에 해당하는 클럽을 검색하고 해당 결과를 Page<Object[]> 형식으로 받아오는 함수
        Page<Object[]> searchPage = clubRepository
                .clubSearchPage(pageable, regionId, sportsId, keyword);

        // 각 결과 항복을 ClubDTO로 변환하는 함수를 정의 → 각 요소를 entityToClubDTO method에 전달.
        Function<Object[], ClubDTO> function = (objectList -> {
            if (ObjectUtils.isEmpty(objectList[1])) {
                List<ClubImage> clubImageList = new ArrayList<>();
                return entityToClubDTO((Club) objectList[0], clubImageList, (Long) objectList[2]);
            }
            return entityToClubDTO((Club) objectList[0], Collections.singletonList((ClubImage) objectList[1]), (Long) objectList[2]);
        });

        // 페이지에 대한 정보와 페이지에 포함된 결과를 저장.
        return new SearchPageResultDTO<>(searchPage, function);
    }

    @Override
    @Transactional
    public Long create(ClubDTO clubDTO) {
        Map<String, Object> clubMap = clubDTOToEntity(clubDTO);
        Club club = (Club) clubMap.get("club");
        clubRepository.save(club);

        @SuppressWarnings("unchecked")
        List<ClubImage> imageList = (List<ClubImage>) clubMap.get("imageList");
        if (!ObjectUtils.isEmpty(imageList)) {
            for (ClubImage clubImage : imageList) clubImageRepository.save(clubImage);
        }

        Long clubId = club.getId();
        // 클럽 멤버 테이블에 record 생성
        ClubMember clubMember = ClubMember.builder()
                .club(Club.builder().id(clubId).build())
                .member(User.builder().id(clubDTO.getLeaderId()).build())
                .rank(100)
                .isActive((byte) 1)
                .isBlacklist((byte) 0)
                .build();
        clubMemberRepository.save(clubMember);

        return clubId;
    }

    @Override
    public ClubDTO getClub(Long clubId) {
        List<Object[]> result = clubRepository.getClubWithEveryImage(clubId);
        Club club = (Club) result.get(0)[0];
        Long personnel = (Long) result.get(0)[1];
        ClubDTO clubDTO = ClubDTO.builder()
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
                .build();

        if (result.size() == 1) {
            if (ObjectUtils.isEmpty(result.get(0)[2])) {
                ImageDTO imageDTO = ImageDTO.builder()
                        .name(club.getSports().getName() + ".jpg")
                        .uuid("uuid")
                        .path("club/main")
                        .build();
                clubDTO.getImageDTOList().add(imageDTO);
            }

        } else {
            for (Object[] objects : result) {
                if (!ObjectUtils.isEmpty(objects[2])) {
                    ClubImage clubImage = (ClubImage) objects[2];
                    ImageDTO imageDTO = ImageDTO.builder()
                            .path(clubImage.getPath())
                            .name(clubImage.getName())
                            .uuid(clubImage.getUuid())
                            .build();
                    clubDTO.getImageDTOList().add(imageDTO);
                }
            }
        }

//        result.forEach(objects -> {
//            ClubImage clubImage = (ClubImage) objects[2];
//            ImageDTO imageDTO = ImageDTO.builder()
//                    .path(clubImage.getPath())
//                    .name(clubImage.getName())
//                    .uuid(clubImage.getUuid())
//                    .build();
//            clubDTO.getImageDTOList().add(imageDTO);
//        });

        return clubDTO;
    }

    @Override
    public NavDTO getNav(Long clubId, Long userId) {
        List<Object[]> resultList = clubRepository.getClubAndClubMemberByClubAndUser(clubId, userId);
        if (ObjectUtils.isEmpty(resultList)) {
            Club club = clubRepository.getClubByClubId(clubId);

            return NavDTO.builder()
                    .clubId(club.getId())
                    .clubName(club.getName())
                    .clubHeadline(club.getHeadline())
                    .isGuest(true)
                    .build();
        } else {
            Object[] result = resultList.get(0);
            Club club = (Club) result[0];
            ClubMember clubMember = (ClubMember) result[1];

            return entityToNavDTO(club, clubMember);
        }
    }

    @Override
    public Long registerClub(Long clubId, Long userId) {
        ClubMember clubMember = ClubMember.builder()
                .club(Club.builder().id(clubId).build())
                .member(User.builder().id(userId).build())
                .rank(0)
                .isActive((byte) 0)
                .isBlacklist((byte) 0)
                .build();
        clubMemberRepository.save(clubMember);

        return clubMember.getId();
    }
}
