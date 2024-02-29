package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubMemberDTO;
import com.swithus.community.club.dto.page.MemberPageRequestDTO;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.user.entity.User;

import java.util.List;

public interface ClubMemberService {
    PageResultDTO<ClubMemberDTO, Object[]> getMemberPage(MemberPageRequestDTO requestDTO);

    List<ClubMemberDTO> getWaitingList(Long clubId);

    Long getClubMemberId(Long clubId, Long userId);

    void changeNickname(Long clubMemberId, String nickname);


    // 이거 UserImage도 넣어서 완성시켜야함
    default ClubMemberDTO entityToClubMemberDTO(ClubMember clubMember, User user) {
        ImageDTO imageDTO = ImageDTO.builder()
                .name("circleUser.png")
                .uuid("uuid")
                .path("profile")
                .build();

        return ClubMemberDTO.builder()
                .clubMemberId(clubMember.getId())
                .clubId(clubMember.getClub().getId())
                .memberId(clubMember.getMember().getId())
                .memberName(user.getName())
                .rank(clubMember.getRank())
                .isActive(clubMember.getIsActive())
                .regDate(clubMember.getRegDate())
                .imageDTO(imageDTO)
                .build();
    }
}
