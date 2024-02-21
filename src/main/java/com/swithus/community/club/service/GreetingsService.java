package com.swithus.community.club.service;

import com.swithus.community.club.dto.GreetingsDTO;
import com.swithus.community.club.dto.page.GreetingsPageRequestDTO;
import com.swithus.community.club.entity.Greetings;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.user.entity.User;

public interface GreetingsService {
    PageResultDTO<GreetingsDTO, Object[]> getGreetingsPage(GreetingsPageRequestDTO greetingsPageRequestDTO);

    GreetingsDTO getGreetings(Long clubId, Long userId);

    GreetingsDTO getGreetingsIdAndLikeCountByGreetingsId(Long greetingsId);

    Greetings getGreetingsById(Long greetingsId);

    Long createGreetings(Long clubMemberId, String content);

    void updateGreetings(Long greetingsId, String content);

    Long createGreetingsLike(Long greetingsId, Long clubMemberId);

    void deleteGreetingsLike(Long greetingsId, Long clubMemberId);

    default GreetingsDTO entityToGreetingsDTO(Greetings greetings, User user, Long likeCount, Long clicked) {
        boolean isClicked;
        isClicked = clicked > 0;

        return GreetingsDTO.builder()
                .greetingsId(greetings.getId())
                .memberName(user.getName())
                .content(greetings.getContent())
                .likeCount(likeCount)
                .clicked(isClicked)
                .build();
    }
}
