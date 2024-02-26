package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.dto.page.ClubPostPageRequestDTO;
import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.entity.ClubPostImage;
import com.swithus.community.club.repository.ClubPostRepository;
import com.swithus.community.club.service.ClubPostService;
import com.swithus.community.global.dto.ImageDTO;
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

    @Override
    public ClubPostDTO getClubPostDTO(Long postId) {
        List<Object[]> result = clubPostRepository.getClubPostDTO(postId);

        ClubPost post = (ClubPost) result.get(0)[0];
        Long replyCount = (Long) result.get(0)[1];
        Long likeCount = (Long) result.get(0)[2];

        ClubPostDTO postDTO = ClubPostDTO.builder()
                .clubPostId(post.getId())
                .clubId(post.getClub().getId())
                .ctgrId(post.getCtgr().getId())
                .ctgrName(post.getCtgr().getName())
                .writerId(post.getWriter().getId())
                .writerName(post.getWriter().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .visitCount(post.getVisitCount())
                .regDate(post.getRegDate())
                .modDate(post.getModDate())
                .replyCount(replyCount)
                .likeCount(likeCount)
                .build();

        result.forEach(objects -> {
            ClubPostImage image = (ClubPostImage) objects[3];
            ImageDTO imageDTO = ImageDTO.builder()
                    .uuid(image.getUuid())
                    .name(image.getName())
                    .path(image.getPath())
                    .build();
            postDTO.getImageDTOList().add(imageDTO);
        });

        return postDTO;
    }
}
