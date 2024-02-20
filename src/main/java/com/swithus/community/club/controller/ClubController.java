package com.swithus.community.club.controller;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.GreetingsDTO;
import com.swithus.community.club.dto.page.GreetingsPageRequestDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.service.ClubService;
import com.swithus.community.club.service.GreetingsService;
import com.swithus.community.global.service.RegionService;
import com.swithus.community.global.service.SportsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {
    private final RegionService regionService;
    private final SportsService sportsService;
    private final ClubService clubService;
    private final GreetingsService greetingsService;

    private final String USER_ID = "userId";
    private final String NAV_DTO = "navDTO";
    private final String CLUB_DTO = "clubDTO";

    // 클럽 서칭 페이지로 이동
    @GetMapping("/search")
    public void search(SearchPageRequestDTO searchPageRequestDTO, Model model, HttpSession session) {
        log.info("GET /club/search");

        Long regionId = searchPageRequestDTO.getRegionId();
        Long sportsId = searchPageRequestDTO.getSportsId();
        log.info("region: {}, sports: {}", regionId, sportsId);

        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("selectedSportsId", sportsId);
        model.addAttribute("regionList", regionService.getRegionList());
        model.addAttribute("sportsList", sportsService.getSportsList());
        model.addAttribute("result", clubService.getSearchPage(searchPageRequestDTO));
    }

    // 클럽 생성 페이지로 이동
    @GetMapping("/create")
    public void goCreate(Model model, HttpServletRequest request) {
        log.info("GET /club/create");

        // 경로 알려줘서 해당 css 파일 적용하기
        model.addAttribute("servletPath", request.getServletPath());
        model.addAttribute("regionList", regionService.getRegionList());
        model.addAttribute("sportsList", sportsService.getSportsList());
    }

    // 클럽 생성 및 해당 클럽으로 이동
    @PostMapping("/createClub")
    public String createClub(ClubDTO clubDTO, @RequestParam("rank") int rank, @RequestParam("point") int point) {
        log.info("POST /club/create");
        clubDTO.setRank(rank);
        clubDTO.setPoint(point);

        Long clubId = clubService.create(clubDTO);
        log.info("Club ID is " + clubId);

        return "redirect:/club/main/" + clubId;
    }

    // 클럽 메인 페이지 이동
    @GetMapping("/main/{clubId}")
    public void goMain(@PathVariable Long clubId, Model model, HttpSession session) {
        log.info("GET /club/main/{}", clubId);
        Long userId = (Long) session.getAttribute(USER_ID);

        model.addAttribute(NAV_DTO, clubService.getNav(clubId, userId));
        model.addAttribute(CLUB_DTO, clubService.getClub(clubId));
    }

    // 1대1 문의 페이지로 이동
    @GetMapping("/inquiry/{clubId}")
    public void goInquiry(@PathVariable Long clubId, Model model, HttpSession session) {
        log.info("GET /club/inquiry/{}", clubId);
        Long userId = (Long) session.getAttribute(USER_ID);

        // 무언가를 해야함
        model.addAttribute(NAV_DTO, clubService.getNav(clubId, userId));
    }

    // 가입 요청 기능 구현
    @GetMapping("/register/{clubId}")
    public String doRegister(@PathVariable Long clubId, Model model, HttpSession session) {
        log.info("GET /club/register/{}", clubId);

        Long clubMemberId = clubService.registerClub(clubId, (Long) session.getAttribute(USER_ID));
        log.info("ClubMemberId: {}", clubMemberId);

        return "redirect:/club/main/" + clubId;
    }

    // 가입 인사 페이지로 이동
    @GetMapping("/greetings/{clubId}")
    public void goGreetings(@PathVariable Long clubId, GreetingsPageRequestDTO requestDTO, Model model, HttpSession session) {
        log.info("GET /club/greetings");
        requestDTO.setClubId(clubId);
        Long userId = (Long) session.getAttribute(USER_ID);
        Long myGreetingsId = greetingsService.getGreetings(clubId, userId).getGreetingsId();

        model.addAttribute("myGreetingsId", myGreetingsId);
        model.addAttribute(NAV_DTO, clubService.getNav(clubId, userId));
        model.addAttribute("result", greetingsService.getGreetingsPage(requestDTO));
    }

    @GetMapping("/greetingsForm/{clubId}")
    public void goGreetingsForm(@PathVariable Long clubId, Model model, HttpSession session) {
        log.info("GET /club/greetingsForm/{}", clubId);
        Long userId = (Long) session.getAttribute(USER_ID);

        model.addAttribute(NAV_DTO, clubService.getNav(clubId, userId));
        model.addAttribute("greetingsDTO", GreetingsDTO.builder().build());
    }

    @GetMapping("/greetingsDetail/{clubId}")
    public void goGreetingsDetail(@PathVariable Long clubId, Model model, HttpSession session) {
        log.info("GET /club/goGreetingsDetail/{}", clubId);
        Long userId = (Long) session.getAttribute(USER_ID);

        model.addAttribute(NAV_DTO, clubService.getNav(clubId, userId));
        model.addAttribute("greetingsDTO", greetingsService.getGreetings(clubId, userId));
    }


    /////////////////////////////////////////////////////////////////////// 공사중

    @GetMapping("/main")
    public void goMainTemp(Model model, HttpSession session) {
        log.info("GET /club/main");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
        model.addAttribute(CLUB_DTO, clubService.getClub(0L));
    }

    @GetMapping("/greetings")
    public void goGreetingsTemp(Model model) {
        log.info("GET /club/greetings");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
    }

    @GetMapping("/member")
    public void goMember(Model model) {
        log.info("GET /club/member");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
    }

    @GetMapping("/calendar")
    public void goCalendar(Model model) {
        log.info("GET /club/calendar");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
    }

    @GetMapping("/meeting")
    public void goMeeting(Model model) {
        log.info("GET /club/meeting");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
    }

    @GetMapping("/board")
    public void goBoard(Model model) {
        log.info("GET /club/board");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
    }
}
