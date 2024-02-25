package com.swithus.community.club.service.impl;

import com.swithus.community.club.entity.ClubMember;
import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.entity.ClubPostLike;
import com.swithus.community.club.repository.ClubPostLikeRepository;
import com.swithus.community.club.service.ClubPostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubPostLikeServiceImpl implements ClubPostLikeService {
    private final ClubPostLikeRepository likeRepository;

    @Override
    public Boolean checkClickedLike(Long postId, Long clubMemberId) {
        return likeRepository.checkExists(postId, clubMemberId);
    }

    @Override
    public void createLike(Long postId, Long clubMemberId) {
        likeRepository.save(ClubPostLike.builder()
                .post(ClubPost.builder().id(postId).build())
                .member(ClubMember.builder().id(clubMemberId).build())
                .build());
    }

    @Override
    public void deleteLike(Long postId, Long clubMemberId) {
        likeRepository.delete(ClubPostLike.builder()
                .post(ClubPost.builder().id(postId).build())
                .member(ClubMember.builder().id(clubMemberId).build())
                .build());
    }
}
