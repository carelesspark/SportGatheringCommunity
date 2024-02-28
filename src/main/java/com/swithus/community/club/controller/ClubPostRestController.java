package com.swithus.community.club.controller;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.service.ClubPostLikeService;
import com.swithus.community.club.service.ClubPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/clubPost")
public class ClubPostRestController {
    private final ClubPostService postService;
    private final ClubPostLikeService likeService;

    @PostMapping("/updateLike")
    public ResponseEntity<ClubPostDTO> updateLike(@RequestParam Long postId,
                                                  @RequestParam Long clubMemberId) {
        log.info("/clubPost/updateLike");

        boolean checkClicked = likeService.checkClickedLike(postId, clubMemberId);
        log.info("checkClicked: {}", checkClicked);

        if (checkClicked) {
            log.info("좋아요 취소");

            likeService.deleteLike(postId, clubMemberId);

            ClubPostDTO postDTO = postService.getClubPostDTO(postId);

            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        }
        log.info("좋아요 추가");

        likeService.createLike(postId, clubMemberId);

        ClubPostDTO postDTO = postService.getClubPostDTO(postId);

        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);

    }

    @PostMapping("/increaseCount")
    public ResponseEntity<Void> increaseVisitCount(@RequestBody Map<String, Long> request) {
        log.info("/clubPost/increaseCount");

        Long postId = request.get("postId");

        postService.increaseVisitCount(postId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
