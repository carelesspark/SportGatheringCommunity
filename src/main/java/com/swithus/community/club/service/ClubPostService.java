package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.dto.page.ClubPostPageRequestDTO;
import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.entity.ClubPostImage;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public interface ClubPostService {
    PageResultDTO<ClubPostDTO, Object[]> getClubPostDTOPage(ClubPostPageRequestDTO pageRequestDTO);

    default ClubPostDTO entityToClubPostDTO(ClubPost post,
                                            Long replyCount,
                                            Long likeCount,
                                            List<ClubPostImage> imageList) {
        List<ImageDTO> imageDTOList;

        if (ObjectUtils.isEmpty(imageList)) {
            imageDTOList = new ArrayList<>();
        } else {
            imageDTOList = imageList
                    .stream()
                    .map(image -> ImageDTO.builder()
                            .path(image.getPath())
                            .uuid(image.getUuid())
                            .name(image.getName())
                            .build())
                    .toList();
        }

        return ClubPostDTO.builder()
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
                .imageDTOList(imageDTOList)
                .build();
    }
}
