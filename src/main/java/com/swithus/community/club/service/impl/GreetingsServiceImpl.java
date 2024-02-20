package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.GreetingsDTO;
import com.swithus.community.club.dto.page.GreetingsPageRequestDTO;
import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.entity.GreetingsImage;
import com.swithus.community.club.repository.GreetingsImageRepository;
import com.swithus.community.club.repository.GreetingsLikeRepository;
import com.swithus.community.club.repository.GreetingsRepository;
import com.swithus.community.club.service.GreetingsService;
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
public class GreetingsServiceImpl implements GreetingsService {
    private final GreetingsRepository greetingsRepository;
    private final GreetingsImageRepository greetingsImageRepository;
    private final GreetingsLikeRepository greetingsLikeRepository;

    @Override
    public PageResultDTO<GreetingsDTO, Object[]> getGreetingsPage(GreetingsPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Long clubId = requestDTO.getClubId();
        Long userId = requestDTO.getUserId();

        Page<Object[]> greetingsPage = greetingsRepository
                .greetingsSearchPage(pageable, clubId, userId);

        Function<Object[], GreetingsDTO> func = (objects -> entityToGreetingsDTO(
                (Greetings) objects[0],
                (GreetingsImage) objects[1],
                (User) objects[2],
                (Long) objects[3]
        ));

        return new PageResultDTO<>(greetingsPage, func);
    }

    @Override
    public GreetingsDTO getGreetings(Long clubId, Long userId) {
        Object[] result = greetingsRepository.getGreetingsByClubAndUser(clubId, userId);
    }
}