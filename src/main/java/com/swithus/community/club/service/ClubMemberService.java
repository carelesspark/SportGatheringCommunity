package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubMemberDTO;
import com.swithus.community.club.dto.page.MemberPageRequestDTO;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.user.entity.User;

public interface ClubMemberService {
    PageResultDTO<ClubMemberDTO, Object[]> getMemberPage(MemberPageRequestDTO requestDTO);

    // 이거 UserImage도 넣어서 완성시켜야함
    default ClubMemberDTO entityToClubMemberDTO(ClubMember clubMember, User user) {
        String temp = "";

        ImageDTO imageDTO = ImageDTO.builder()
                .name(temp)
                .uuid(temp)
                .path(temp)
                .build();

        return ClubMemberDTO.builder()
                .clubMemberId(clubMember.getId())
                .clubId(clubMember.getClub().getId())
                .memberId(clubMember.getMember().getId())
                .memberName(user.getName())
                .rank(clubMember.getRank())
                .isActive(clubMember.getIsActive())
                .isBlacklist(clubMember.getIsBlacklist())
                .blacklistReason(clubMember.getBlacklistReason())
                .regDate(clubMember.getRegDate())
                .imageDTO(imageDTO)
                .build();
    }

}
