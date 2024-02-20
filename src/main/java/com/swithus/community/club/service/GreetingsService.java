package com.swithus.community.club.service;

import com.swithus.community.club.dto.GreetingsDTO;
import com.swithus.community.club.dto.page.GreetingsPageRequestDTO;
import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.entity.GreetingsImage;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.user.entity.User;

public interface GreetingsService {
    PageResultDTO<GreetingsDTO, Object[]> getGreetingsPage(GreetingsPageRequestDTO greetingsPageRequestDTO);

    GreetingsDTO getGreetings(Long clubId, Long userId);

    default GreetingsDTO entityToGreetingsDTO(Greetings greetings, GreetingsImage greetingsImage, User user, Long likeCount) {
        return GreetingsDTO.builder()
                .greetingsId(greetings.getId())
                .memberName(user.getName())
                .content(greetings.getContent())
                .imageDTO(ImageDTO.builder()
                        .name(greetingsImage.getName())
                        .uuid(greetingsImage.getUuid())
                        .path(greetingsImage.getPath())
                        .build())
                .likeCount(likeCount)
                .build();
    }
}
