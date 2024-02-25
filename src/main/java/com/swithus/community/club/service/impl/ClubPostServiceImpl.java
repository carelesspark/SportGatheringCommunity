package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.dto.page.ClubPostPageRequestDTO;
import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.entity.ClubPostImage;
import com.swithus.community.club.repository.ClubPostRepository;
import com.swithus.community.club.service.ClubPostService;
import com.swithus.community.global.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

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

        Function<Object[], ClubPostDTO> function = (objects -> {
            if (ObjectUtils.isEmpty(objects[3])) {
                List<ClubPostImage> clubPostImageList = new ArrayList<>();

                return entityToClubPostDTO(
                        (ClubPost) objects[0],
                        (Long) objects[1],
                        (Long) objects[2],
                        clubPostImageList);
            }
            return entityToClubPostDTO(
                    (ClubPost) objects[0],
                    (Long) objects[1],
                    (Long) objects[2],
                    Collections.singletonList((ClubPostImage) objects[3]));
        });

        return new PageResultDTO<>(clubPostPage, function);
    }
}
