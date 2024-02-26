package com.swithus.community.club.service;

public interface ClubPostLikeService {
    Boolean checkClickedLike(Long postId, Long clubMemberId);

    void createLike(Long postId, Long clubMemberId);

    void deleteLike(Long postId, Long clubMemberId);
}
