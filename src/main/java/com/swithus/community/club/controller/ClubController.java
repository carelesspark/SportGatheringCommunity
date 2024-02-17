package com.swithus.community.club.controller;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.service.ClubService;
import com.swithus.community.global.service.RegionService;
import com.swithus.community.global.service.SportsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    private final RegionService regionService;
    private final SportsService sportsService;

    @GetMapping("/search")
    public void search(SearchPageRequestDTO pageRequestDTO, Model model, HttpSession session) {
        log.info("GET /club/search");

        Long regionId = pageRequestDTO.getRegionId();
        Long sportsId = pageRequestDTO.getSportsId();
        log.info("region: {}, sports: {}", regionId, sportsId);

        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("selectedSportsId", sportsId);
        model.addAttribute("regionList", regionService.getRegionList());
        model.addAttribute("sportsList", sportsService.getSportsList());
        model.addAttribute("result", clubService.getSearchPage(pageRequestDTO));
    }

    @GetMapping("/create")
    public void goCreate(Model model, HttpServletRequest request) {
        log.info("GET /club/create");

        // 경로 알려줘서 해당 css 파일 적용하기
        model.addAttribute("servletPath", request.getServletPath());
        // 세션에 userId가 존재해야함
        model.addAttribute("regionList", regionService.getRegionList());
        model.addAttribute("sportsList", sportsService.getSportsList());
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

    @GetMapping("/inquiry")
    public void goInquiry() {

    }


    /////////////////////////////////////////////////////////////////////// 공사중

    @GetMapping("/main")
    public void goMainTemp(Model model) {
        log.info("GET /club/main");
    }

    @GetMapping("/greetings")
    public void goGreetingsTemp() {
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
