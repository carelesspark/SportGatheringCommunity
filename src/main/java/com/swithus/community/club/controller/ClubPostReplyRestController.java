package com.swithus.community.club.controller;

import com.swithus.community.club.dto.ClubPostReplyDTO;
import com.swithus.community.club.service.ClubPostReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/clubPostReply")
public class ClubPostReplyRestController {
    private final ClubPostReplyService replyService;

    // C
    @PostMapping("/{postId}")
    public ResponseEntity<Long> createReply(
            @PathVariable("postId") Long postId,
            @RequestBody ClubPostReplyDTO replyDTO) {
        log.info("POST /clubPostReply/{}", postId);

        Long replyId = replyService.createReply(replyDTO);

        return new ResponseEntity<>(replyId, HttpStatus.CREATED);
    }

    // R
    @GetMapping("/{postId}")
    public ResponseEntity<List<ClubPostReplyDTO>> getReplyDTOList(
            @PathVariable("postId") Long postId) {
        log.info("GET /clubPostReply/{}", postId);

        List<ClubPostReplyDTO> replyDTOList = replyService
                .getReplyListByClubPost(postId);

        return new ResponseEntity<>(replyDTOList, HttpStatus.OK);
    }

    // U
    @PutMapping("/{postId}/{replyId}")
    public ResponseEntity<Long> updateReply(
            @PathVariable("postId") Long postId,
            @PathVariable("replyId") Long replyId,
            @RequestBody ClubPostReplyDTO replyDTO) {
        log.info("PUT /clubPostReply/{}/{}", postId, replyId);

        replyService.updateReply(replyDTO);

        return new ResponseEntity<>(replyId, HttpStatus.OK);
    }

    // D
    @DeleteMapping("/{postId}/{replyId}")
    public ResponseEntity<Long> deleteReply(
            @PathVariable("postId") Long postId,
            @PathVariable("replyId") Long replyId) {
        log.info("DELETE /clubPostReply/{}/{}", postId, replyId);

        replyService.deleteReply(replyId);

        return new ResponseEntity<>(replyId, HttpStatus.OK);
    }
}
