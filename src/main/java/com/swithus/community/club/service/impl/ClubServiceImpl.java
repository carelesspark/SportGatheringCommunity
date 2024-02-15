package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.dto.page.SearchPageResultDTO;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.ClubImage;
import com.swithus.community.club.repository.ClubImageRepository;
import com.swithus.community.club.repository.ClubRepository;
import com.swithus.community.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.function.Function;
@Service
@Log4j2
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubImageRepository clubImageRepository;

    @Override
    public SearchPageResultDTO<ClubDTO, Object[]> getSearchPage(SearchPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("key").descending());

        Page<Object[]> searchPage = clubRepository.clubSearchPage(pageable, requestDTO.getRegion(), requestDTO.getSports(), requestDTO.getKeyword());
        Function<Object[], ClubDTO> func = (e -> entityToClubDTO(
                (Club) e[0],
                Collections.singletonList((ClubImage) e[1]),
                (Long) e[2]));

        return new SearchPageResultDTO<>(searchPage, func);
    }
}
