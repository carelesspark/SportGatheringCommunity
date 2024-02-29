package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubMemberDTO;
import com.swithus.community.club.dto.page.MemberPageRequestDTO;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.repository.ClubMemberRepository;
import com.swithus.community.club.service.ClubMemberService;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberService {
    private final ClubMemberRepository clubMemberRepository;

    @Override
    public PageResultDTO<ClubMemberDTO, Object[]> getMemberPage(MemberPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
        Long clubId = requestDTO.getClubId();

        Page<Object[]> memberPage = clubMemberRepository.memberPage(pageable, clubId);

        Function<Object[], ClubMemberDTO> func = (objects -> entityToClubMemberDTO(
                (ClubMember) objects[0],
                (User) objects[1]
        ));

        return new PageResultDTO<>(memberPage, func);
    }

    @Override
    public List<ClubMemberDTO> getWaitingList(Long clubId) {
        List<ClubMember> memberList = clubMemberRepository.getRankZeroClubMemberList(clubId);


        if (memberList == null) {
            return Collections.emptyList();
        }
        log.info("대기자 인원수: {}", memberList.size());

        return memberList.stream().map(clubMember ->
                ClubMemberDTO.builder()
                        .clubMemberId(clubMember.getId())
                        .clubId(clubMember.getClub().getId())
                        .memberId(clubMember.getMember().getId())
                        .memberName(clubMember.getNickname())
                        .rank(clubMember.getRank())
                        .isActive(clubMember.getIsActive())
                        .regDate(clubMember.getRegDate())
                        .build()
        ).toList();
    }

    @Override
    public Long getClubMemberId(Long clubId, Long userId) {
        ClubMember clubMember = clubMemberRepository.getClubMemberIdByClubAndUser(clubId, userId);

        return clubMember == null ? null : clubMember.getId();
    }

    @Override
    @Transactional
    public void changeNickname(Long clubMemberId, String nickname) {
        clubMemberRepository.updateNickname(clubMemberId, nickname);
    }
}
