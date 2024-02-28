package com.swithus.community.club.controller;

import com.swithus.community.club.dto.ClubPostDTO;
import com.swithus.community.club.service.ClubPostLikeService;
import com.swithus.community.club.service.ClubPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        if (checkClicked) {
            likeService.deleteLike(postId, clubMemberId);

            ClubPostDTO postDTO = postService.getClubPostDTO(postId);

            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        } else {
            likeService.createLike(postId, clubMemberId);

            ClubPostDTO postDTO = postService.getClubPostDTO(postId);

            return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
        }
    }

    @PostMapping("/increaseCount")
    public ResponseEntity<Void> increaseVisitCount(@RequestParam Long postId) {
        log.info("/clubPost/increaseCount");

        postService.increaseVisitCount(postId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
