package com.swithus.community.club.service;

import com.swithus.community.club.dto.ClubPostReplyDTO;

import java.util.List;

public interface ClubPostReplyService {
    List<ClubPostReplyDTO> getReplyListByClubPost(Long postId);

    Long createReply(ClubPostReplyDTO replyDTO);
}
