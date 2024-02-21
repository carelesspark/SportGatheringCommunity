package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.GreetingsDTO;
import com.swithus.community.club.dto.page.GreetingsPageRequestDTO;
import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.entity.Greetings;
import com.swithus.community.club.entity.GreetingsImage;
import com.swithus.community.club.entity.GreetingsLike;
import com.swithus.community.club.repository.GreetingsImageRepository;
import com.swithus.community.club.repository.GreetingsLikeRepository;
import com.swithus.community.club.repository.GreetingsRepository;
import com.swithus.community.club.service.GreetingsService;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import com.swithus.community.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
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
                (User) objects[1],
                (Long) objects[2],
                (Long) objects[3]
        ));

        return new PageResultDTO<>(greetingsPage, func);
    }

    @Override
    public GreetingsDTO getGreetings(Long clubId, Long userId) {
        List<Object[]> resultList = greetingsRepository.getGreetingsByClubAndUser(clubId, userId);
        if (ObjectUtils.isEmpty(resultList)) {

            return GreetingsDTO.builder().build();
        }
        Object[] result = resultList.get(0);
        Greetings greetings = (Greetings) result[0];
        GreetingsImage greetingsImage = (GreetingsImage) result[1];
        ImageDTO imageDTO = null;
        if (greetingsImage != null) {
            imageDTO = ImageDTO.builder()
                    .uuid(greetingsImage.getUuid())
                    .path(greetingsImage.getPath())
                    .name(greetingsImage.getName())
                    .build();
        }

        return GreetingsDTO.builder()
                .greetingsId(greetings.getId())
                .memberName(null) // 필요 없음 → 이건 가입인사 생성 및 수정일 때는 세션에서 가져와서 사용할 거임
                .content(greetings.getContent())
                .imageDTO(imageDTO)
                .build();
    }

    @Override
    public GreetingsDTO getGreetingsIdAndLikeCountByGreetingsId(Long greetingsId) {
        List<Object[]> resultList = greetingsRepository.getGreetingsAndLikeCountByGreetingsId(greetingsId);
        if (ObjectUtils.isEmpty(resultList)) {
            return GreetingsDTO.builder().build();
        }
        Object[] result = resultList.get(0);
        Greetings greetings = (Greetings) result[0];
        Long likeCount = (Long) result[1];

        return GreetingsDTO.builder()
                .greetingsId(greetings.getId())
                .content(greetings.getContent())
                .likeCount(likeCount)
                .build();
    }

    @Override
    public Greetings getGreetingsById(Long greetingsId) {
        Optional<Greetings> greetings = greetingsRepository.findById(greetingsId);

        return greetings.orElseGet(Greetings::new);
    }

    @Override
    public Long createGreetings(Long clubMemberId, String content) {
        Greetings greetings = Greetings.builder()
                .member(ClubMember.builder()
                        .id(clubMemberId)
                        .build())
                .content(content)
                .build();

        greetingsRepository.save(greetings);

        return greetings.getId();
    }

    @Override
    @Transactional
    public void updateGreetings(Long greetingsId, String content) {
        greetingsRepository.updateGreetings(greetingsId, content);
    }

    @Override
    public Long createGreetingsLike(Long greetingsId, Long clubMemberId) {
        GreetingsLike greetingsLike = GreetingsLike.builder()
                .greetings(Greetings.builder().id(greetingsId).build())
                .member(ClubMember.builder().id(clubMemberId).build())
                .build();

        greetingsLikeRepository.save(greetingsLike);

        return greetingsLike.getId();
    }

    @Override
    public void deleteGreetingsLike(Long greetingsId, Long clubMemberId) {
        greetingsLikeRepository.deleteByGreetingsIdAndClubMemberId(greetingsId, clubMemberId);
    }
}