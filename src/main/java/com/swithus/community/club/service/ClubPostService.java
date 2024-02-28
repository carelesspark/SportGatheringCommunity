package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.dto.page.ClubPostPageRequestDTO;
import com.swithus.community.club.entity.*;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ClubPostService {
    PageResultDTO<ClubPostDTO, Object[]> getClubPostDTOPage(ClubPostPageRequestDTO pageRequestDTO);

    ClubPostDTO getClubPostDTO(Long postId);

    Long createPost(ClubPostDTO postDTO);

    void updatePost(ClubPostDTO postDTO);

    void increaseVisitCount(Long postId);

    void deleteClubPost(Long postId);

    default Map<String, Object> clubPostDTOToEntity(ClubPostDTO postDTO) {
        Map<String, Object> postMap = new HashMap<>();

        ClubPost post = ClubPost.builder()
                .id(postDTO.getClubPostId())
                .ctgr(ClubPostCtgr.builder().id(postDTO.getCtgrId()).build())
                .club(Club.builder().id(postDTO.getClubId()).build())
                .writer(ClubMember.builder().id(postDTO.getWriterId()).build())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .visitCount(postDTO.getVisitCount())
                .build();
        postMap.put("post", post);

        if(postDTO.getImageDTOList() != null){
            List<ImageDTO> imageDTOList = postDTO.getImageDTOList();

            List<ClubPostImage> imageList = new ArrayList<>();
            if (imageDTOList != null && !imageDTOList.isEmpty()) {
                imageList = imageDTOList.stream()
                        .map(imageDTO -> ClubPostImage.builder()
                                .post(post)
                                .path(imageDTO.getPath())
                                .name(imageDTO.getName())
                                .uuid(imageDTO.getUuid())
                                .build())
                        .toList();
            }
            postMap.put("imageList", imageList);
        }

        return postMap;
    }

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
