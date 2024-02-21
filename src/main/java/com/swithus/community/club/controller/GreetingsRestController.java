package com.swithus.community.club.controller;

import com.swithus.community.club.dto.GreetingsDTO;
import com.swithus.community.club.service.ClubMemberService;
import com.swithus.community.club.service.GreetingsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/greetings")
public class GreetingsRestController {
    private final GreetingsService greetingsService;
    private final ClubMemberService clubMemberService;

    private final String USER_ID = "userId";

    @PostMapping("/increaseLike")
    public ResponseEntity<GreetingsDTO> increaseLike(@RequestParam Long greetingsId,
                                                     @RequestParam Long clubId,
                                                     HttpSession session) {
        log.info("/greetings/increaseLike");
        Long userId = (Long) session.getAttribute(USER_ID);

        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        Long greetingsLikeId = greetingsService.createGreetingsLike(greetingsId, clubMemberId);
        log.info("GreetingsLike ID: {}", greetingsLikeId);

        GreetingsDTO greetingsDTO = greetingsService.getGreetingsIdAndLikeCountByGreetingsId(greetingsId);

        return new ResponseEntity<>(greetingsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/decreaseLike")
    public ResponseEntity<GreetingsDTO> decreaseLike(@RequestParam Long greetingsId,
                                                     @RequestParam Long clubId,
                                                     HttpSession session) {
        log.info("/greetings/decreaseLike");
        Long userId = (Long) session.getAttribute(USER_ID);

        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        greetingsService.deleteGreetingsLike(greetingsId, clubMemberId);

        GreetingsDTO greetingsDTO = greetingsService.getGreetingsIdAndLikeCountByGreetingsId(greetingsId);

        return new ResponseEntity<>(greetingsDTO, HttpStatus.OK);
    }
}
