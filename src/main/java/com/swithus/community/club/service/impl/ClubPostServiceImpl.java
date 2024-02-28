package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.dto.page.ClubPostPageRequestDTO;
import com.swithus.community.club.entity.*;
import com.swithus.community.club.repository.ClubPostImageRepository;
import com.swithus.community.club.repository.ClubPostLikeRepository;
import com.swithus.community.club.repository.ClubPostReplyRepository;
import com.swithus.community.club.repository.ClubPostRepository;
import com.swithus.community.club.service.ClubPostService;
import com.swithus.community.global.dto.ImageDTO;
import com.swithus.community.global.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubPostServiceImpl implements ClubPostService {
    private final ClubPostRepository clubPostRepository;
    private final ClubPostImageRepository clubPostImageRepository;
    private final ClubPostReplyRepository clubPostReplyRepository;
    private final ClubPostLikeRepository clubPostLikeRepository;

    @Value("${image.folder}")
    private String imageFolder;

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

        log.info("개수: {}", result.size());

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
            if (objects[3] != null) {
                ClubPostImage image = (ClubPostImage) objects[3];
                ImageDTO imageDTO = ImageDTO.builder()
                        .uuid(image.getUuid())
                        .name(image.getName())
                        .path(image.getPath())
                        .build();
                postDTO.getImageDTOList().add(imageDTO);
            }
        });

        return postDTO;
    }

    @Override
    @Transactional
    public Long createPost(ClubPostDTO postDTO) {
        Map<String, Object> postMap = clubPostDTOToEntity(postDTO);
        ClubPost post = (ClubPost) postMap.get("post");
        clubPostRepository.save(post);

        Object possibleImageList = postMap.get("imageList");
        if (!ObjectUtils.isEmpty(possibleImageList)) {
            List<ClubPostImage> imageList = (List<ClubPostImage>) possibleImageList;

            for (ClubPostImage image : imageList) clubPostImageRepository.save(image);
        }

        return post.getId();
    }

    @Override
    public void updatePost(ClubPostDTO postDTO) {
        ClubPost post = ClubPost.builder()
                .id(postDTO.getClubPostId())
                .ctgr(ClubPostCtgr.builder().id(postDTO.getCtgrId()).build())
                .club(Club.builder().id(postDTO.getClubId()).build())
                .writer(ClubMember.builder().id(postDTO.getWriterId()).build())
                .title(postDTO.getTitle())
                .content(postDTO.getContent())
                .build();

        clubPostRepository.save(post);
    }

    @Override
    @Transactional
    public void increaseVisitCount(Long postId) {
        clubPostRepository.increaseVisitCount(postId);
    }

    @Override
    public void deleteClubPost(Long postId) {
        // 이미지 삭제
        List<ClubPostImage> imageList = clubPostImageRepository.getClubPostImageListByClubPostId(postId);
        if (!ObjectUtils.isEmpty(imageList)) {
            imageList.forEach(image -> {
                Long imageId = image.getId();
                String path = image.getPath();
                String uuid = image.getUuid();
                String name = image.getName();

                String fileName = path + "/" + uuid + "_" + name;
                Path filePath = Paths.get(imageFolder, fileName);
                Path thumbnailPath = Paths.get(filePath.getParent().toString(), "s_" + filePath.getFileName().toString());
                try {
                    Files.delete(filePath);
                    Files.delete(thumbnailPath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                clubPostImageRepository.deleteById(imageId);
            });
        }

        // 댓글 삭제
        List<ClubPostReply> replyList = clubPostReplyRepository.findListByPost(ClubPost.builder().id(postId).build());
        if (!ObjectUtils.isEmpty(replyList)) {
            replyList.forEach(reply -> clubPostReplyRepository.deleteById(reply.getId()));
        }

        // 좋아요 삭제
        List<ClubPostLike> likeList = clubPostLikeRepository.getLikeListByClubPostId(postId);
        if (!ObjectUtils.isEmpty(likeList)) {
            likeList.forEach(like -> clubPostLikeRepository.deleteById(like.getId()));
        }

        // 게시글 삭제
        clubPostRepository.deleteById(postId);
    }
}
