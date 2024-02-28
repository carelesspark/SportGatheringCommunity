package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.NavDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.ClubImage;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.repository.ClubImageRepository;
import com.swithus.community.club.repository.ClubMemberRepository;
import com.swithus.community.club.repository.ClubRepository;
import com.swithus.community.club.service.ClubService;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.main.dto.PopularClubDTO;
import com.swithus.community.manager.repository.UserDetailRepository;
import com.swithus.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
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

    private final UserDetailRepository userDetailRepository;

    @Override
    public PageResultDTO<ClubDTO, Object[]> getSearchPage(SearchPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Long regionId = requestDTO.getRegionId();
        Long sportsId = requestDTO.getSportsId();
        String keyword = requestDTO.getKeyword();

        // 조건에 해당하는 클럽을 검색하고 해당 결과를 Page<Object[]> 형식으로 받아오는 함수
        Page<Object[]> searchPage = clubRepository.clubSearchPage(pageable, regionId, sportsId, keyword);

        // 각 결과 항복을 ClubDTO로 변환하는 함수를 정의 → 각 요소를 entityToClubDTO method에 전달.
        Function<Object[], ClubDTO> function = (objectList -> {
            if (ObjectUtils.isEmpty(objectList[1])) {
                List<ClubImage> clubImageList = new ArrayList<>();
                return entityToClubDTO((Club) objectList[0], clubImageList, (Long) objectList[2]);
            }
            return entityToClubDTO((Club) objectList[0], Collections.singletonList((ClubImage) objectList[1]), (Long) objectList[2]);
        });

        // 페이지에 대한 정보와 페이지에 포함된 결과를 저장.
        return new PageResultDTO<>(searchPage, function);
    }

    @Override
    @Transactional
    public Long create(ClubDTO clubDTO) {
        Map<String, Object> clubMap = clubDTOToEntity(clubDTO);
        Club club = (Club) clubMap.get("club");
        clubRepository.save(club);

        @SuppressWarnings("unchecked") List<ClubImage> imageList = (List<ClubImage>) clubMap.get("imageList");
        if (!ObjectUtils.isEmpty(imageList)) {
            for (ClubImage clubImage : imageList) clubImageRepository.save(clubImage);
        }

        Long clubId = club.getId();
        User user = userDetailRepository.getReferenceById(clubDTO.getLeaderId());
        // 클럽 멤버 테이블에 record 생성
        ClubMember clubMember = ClubMember.builder()
                .club(Club.builder().id(clubId).build()).member(User.builder().id(user.getId()).build())
                .nickname(user.getNickname()).rank(100).isActive((byte) 1).build();
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

        return clubDTO;
    }

    @Override
    public NavDTO getNavDTO(Long clubId, Long clubMemberId) {
        if (clubMemberId == null) {
            Club club = clubRepository.getReferenceById(clubId);

            return NavDTO.builder().clubId(club.getId())
                    .clubName(club.getName())
                    .clubHeadline(club.getHeadline())
                    .clubMemberId(null)
                    .nickname(null)
                    .isGuest(true)
                    .isWaiting(false)
                    .isMember(false)
                    .isLeader(false)
                    .build();
        }
        List<Object[]> objectList = clubRepository
                .getClubAndClubMemberByClubIdAndClubMemberId(clubId, clubMemberId);

        Object[] result = objectList.get(0);
        Club club = (Club) result[0];
        ClubMember clubMember = (ClubMember) result[1];

        boolean isWaiting = clubMember.getRank() == 0;
        boolean isMember = clubMember.getRank() > 0;
        boolean isLeader = clubMember.getRank() == 100;

        return NavDTO.builder()
                .clubId(club.getId())
                .clubName(club.getName())
                .clubHeadline(club.getHeadline())
                .clubMemberId(clubMember.getId())
                .nickname(clubMember.getNickname())
                .isGuest(false).isWaiting(isWaiting)
                .isMember(isMember)
                .isLeader(isLeader)
                .build();
    }

    @Override
    public Long registerClub(Long clubId, Long userId) {
        User user = userDetailRepository.getReferenceById(userId);

        ClubMember clubMember = ClubMember.builder()
                .club(Club.builder().id(clubId).build())
                .member(User.builder().id(user.getId()).build())
                .nickname(user.getNickname())
                .rank(0)
                .isActive((byte) 1)
                .build();
        clubMemberRepository.save(clubMember);

        return clubMember.getId();
    }


    @Override
    public List<PopularClubDTO> getPopularClubDTOList(int number) {
        LocalDateTime now = LocalDateTime.now();

        Pageable pageable = PageRequest.of(0, number);

        List<Object[]> resultList = clubRepository
                .getClubAndMemberCountAndMeetingCountAndImageLimitByNumber(now, pageable);
        if (ObjectUtils.isEmpty(resultList)) return null;

        return resultList.stream().map(result -> {
            Club club = (Club) result[0];
            Long memberCount = (Long) result[1];
            Long meetingCount = (Long) result[2];
            // 비어있을 수 있음
            Object possibleImageList = result[3];

            List<ImageDTO> imageDTOList = new ArrayList<>();

            if (possibleImageList == null) {
                ImageDTO imageDTO = ImageDTO.builder()
                        .uuid("uuid")
                        .name(club.getSports().getName() + ".jpg")
                        .path("club/main").build();

                imageDTOList.add(imageDTO);
            } else {
                if (possibleImageList instanceof ClubImage) {
                    ClubImage clubImage = (ClubImage) possibleImageList;
                    ImageDTO imageDTO = ImageDTO.builder()
                            .uuid(clubImage.getUuid())
                            .name(clubImage.getName())
                            .path(clubImage.getPath())
                            .build();
                    imageDTOList.add(imageDTO);
                } else {
                    List<ClubImage> clubImageList = (List<ClubImage>) possibleImageList;
                    imageDTOList = clubImageList.stream()
                            .map(image -> ImageDTO.builder()
                                    .path(image.getPath())
                                    .name(image.getName())
                                    .uuid(image.getUuid())
                                    .build())
                            .toList();
                }
            }

            return PopularClubDTO.builder()
                    .clubId(club.getId())
                    .regionName(club.getRegion().getName())
                    .sportsName(club.getSports().getName())
                    .name(club.getName())
                    .headline(club.getHeadline())
                    .memberCount(memberCount)
                    .meetingCount(meetingCount)
                    .imageDTOList(imageDTOList)
                    .build();
        }).toList();
    }

    @Override
    public List<Club> findUsersClub(String nickname) {
        return clubRepository.findByUserNickname(nickname);
    }
}
