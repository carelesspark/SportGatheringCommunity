package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.dto.page.ClubPostPageRequestDTO;
import com.swithus.community.club.repository.ClubPostRepository;
import com.swithus.community.club.service.ClubPostService;
import com.swithus.community.global.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubPostServiceImpl implements ClubPostService {
    private final ClubPostRepository clubPostRepository;

    @Override
    public PageResultDTO<ClubPostDTO, Object[]> getClubPostDTOPage(ClubPostPageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO
                .getPageable(Sort.by("id").descending());

        Long clubId = pageRequestDTO.getClubId();
        Long ctgrId = pageRequestDTO.getCtgrId();
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        Page<Object[]> clubPostPage = clubPostRepository
                .clubPostPage(pageable, clubId, ctgrId, type, keyword);

        return null;
    }
}
