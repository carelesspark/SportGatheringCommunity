package com.swithus.community.club.service.impl;

import com.swithus.community.club.dto.ClubPostReplyDTO;
import com.swithus.community.club.entity.ClubPost;
import com.swithus.community.club.entity.ClubPostReply;
import com.swithus.community.club.repository.ClubPostReplyRepository;
import com.swithus.community.club.service.ClubPostReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubPostReplyServiceImpl implements ClubPostReplyService {
    private final ClubPostReplyRepository replyRepository;


    @Override
    public List<ClubPostReplyDTO> getReplyListByClubPost(Long postId) {
        ClubPost post = ClubPost.builder().id(postId).build();
        List<ClubPostReply> result = replyRepository.findListByPost(post);

        return result.stream().map(reply ->
                ClubPostReplyDTO.builder()
                        .replyId(reply.getId())
                        .postId(reply.getPost().getId())
                        .writerId(reply.getWriter().getId())
                        .nickname(reply.getWriter().getNickname())
                        .comment(reply.getComment())
                        .regDate(reply.getRegDate())
                        .modDate(reply.getModDate())
                        .build()).toList();
    }
}
