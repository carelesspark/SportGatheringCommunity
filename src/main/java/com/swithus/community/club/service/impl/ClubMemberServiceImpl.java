package com.swithus.community.club.service.impl;

import com.swithus.community.club.repository.ClubMemberRepository;
import com.swithus.community.club.service.ClubMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubMemberServiceImpl implements ClubMemberService {
    private final ClubMemberRepository clubMemberRepository;


}
