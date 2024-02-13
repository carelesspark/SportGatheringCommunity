package com.swithus.community.club.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    @GetMapping("/search")
    public void goSearch() {
        log.info("GET /club/search");
    }

    @GetMapping("/main")
    public void goMain() {
        log.info("GET /club/main");
    }

    @GetMapping("/greetings")
    public void goGreetings() {
        log.info("GET /club/greetings");
    }

    @GetMapping("/member")
    public void goMember() {
        log.info("GET /club/member");
    }

    @GetMapping("/calendar")
    public void goCalendar() {
        log.info("GET /club/calendar");
    }

    @GetMapping("/meeting")
    public void goMeeting() {
        log.info("GET /club/meeting");
    }

    @GetMapping("/board")
    public void goBoard() {
        log.info("GET /club/board");
    }
}
