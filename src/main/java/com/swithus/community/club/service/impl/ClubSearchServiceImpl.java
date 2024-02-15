package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.SearchPageRequestDTO;
import com.swithus.community.club.dto.SearchPageResultDTO;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.entity.ClubImage;
import com.swithus.community.club.repository.search.ClubSearchRepository;
import com.swithus.community.club.service.ClubSearchService;
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
public class ClubSearchServiceImpl implements ClubSearchService {
    private final ClubSearchRepository searchRepository;

    @Override
    public SearchPageResultDTO<ClubDTO, Object[]> getSearchPage(SearchPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<Object[]> clubSearchPage = searchRepository.clubSearchPage(pageable, requestDTO.getRegion(), requestDTO.getSports(), requestDTO.getKeyword());

        Function<Object[], ClubDTO> func = (e -> entityToDTO(
                (Club) e[0],
                Collections.singletonList((ClubImage) e[1]),
                (Long) e[2]));

        return new SearchPageResultDTO<>(clubSearchPage, func);
    }
}
