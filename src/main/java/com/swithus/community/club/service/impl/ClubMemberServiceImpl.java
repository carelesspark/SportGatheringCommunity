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

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberService {
    private final ClubMemberRepository clubMemberRepository;

    @Override
    public PageResultDTO<ClubMemberDTO, Object[]> getMemberPage(MemberPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Long clubId = requestDTO.getClubId();

        Page<Object[]> memberPage = clubMemberRepository.memberPage(pageable, clubId);

        Function<Object[], ClubMemberDTO> func = (objects -> entityToClubMemberDTO(
                (ClubMember) objects[0],
                (User) objects[1]
        ));

        return new PageResultDTO<>(memberPage, func);
    }
}
