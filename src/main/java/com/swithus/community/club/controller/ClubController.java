package com.swithus.community.club.controller;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {
    private final ClubService clubService;

    @GetMapping("/search")
    public void search(SearchPageRequestDTO pageRequestDTO, Model model) {
        log.info("GET /club/search");

        // model.addAttribute("result", clubService.getSearchPage(pageRequestDTO));
    }

    @GetMapping("/create")
    public void goCreate() {
        log.info("GET /club/create");
    }

    @PostMapping("/create")
    public String createClub(ClubDTO clubDTO) {
        log.info("POST /club/create");

        Long clubId = clubService.create(clubDTO);
        log.info("Club ID is " + clubId);

        return "redirect:/club/main/" + clubId;
    }

    @GetMapping("/main/{clubId}")
    public void goMain(@PathVariable Long clubId, Model model) {
        log.info("GET /club/main/{}", clubId);

        ClubDTO clubDTO = clubService.getClub(clubId);
        model.addAttribute("dto", clubDTO);
    }
    
    /////////////////////////////////////////////////////////////////////// 공사중

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
