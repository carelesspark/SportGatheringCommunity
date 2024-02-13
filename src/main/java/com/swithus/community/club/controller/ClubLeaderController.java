package com.swithus.community.club.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/club/leader")
public class ClubLeaderController {

    @GetMapping("/main")
    public void goMain() {
        log.info("GET /club/leader/main");
    }
}
