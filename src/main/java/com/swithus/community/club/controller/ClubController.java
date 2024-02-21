package com.swithus.community.club.controller;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.dto.GreetingsDTO;
import com.swithus.community.club.dto.page.GreetingsPageRequestDTO;
import com.swithus.community.club.dto.page.MemberPageRequestDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.service.ClubMemberService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {
    private final RegionService regionService;
    private final SportsService sportsService;
    private final ClubService clubService;
    private final ClubMemberService clubMemberService;
    private final GreetingsService greetingsService;

    private final String USER_ID = "userId";
    private final String NAV_DTO = "navDTO";
    private final String CLUB_DTO = "clubDTO";
    private final String RESULT = "result";

    // 임시 함수
    private void putSession(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", 3L);
            session.setAttribute("userName", "b");
        }
    }

    // 클럽 서칭 페이지로 이동
    @GetMapping("/search")
    public void search(SearchPageRequestDTO pageRequestDTO,
                       Model model,
                       HttpSession session) {
        log.info("GET /club/search");

        putSession(session);

        Long regionId = pageRequestDTO.getRegionId();
        Long sportsId = pageRequestDTO.getSportsId();
        String keyword = pageRequestDTO.getKeyword();
        log.info("region: {}, sports: {}", regionId, sportsId);

        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("selectedSportsId", sportsId);
        model.addAttribute("searchKeyword", keyword);
        model.addAttribute("regionList", regionService.getRegionList());
        model.addAttribute("sportsList", sportsService.getSportsList());
        model.addAttribute(RESULT, clubService.getSearchPage(pageRequestDTO));
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
        log.info("Club ID: {}", clubId);

        return "redirect:/club/main?clubId=" + clubId;
    }

    // 클럽 메인 페이지 이동
    @GetMapping("/main")
    public void goMain(@RequestParam Long clubId, Model model, HttpSession session) {
        log.info("GET /club/main?clubId={}", clubId);

        model.addAttribute(NAV_DTO, clubService.getNav(clubId, (Long) session.getAttribute(USER_ID)));
        model.addAttribute(CLUB_DTO, clubService.getClub(clubId));
    }

    // 가입 요청 기능 구현
    @GetMapping("/register")
    public String doRegister(@RequestParam Long clubId, Model model, HttpSession session) {
        log.info("GET /club/register?clubId={}", clubId);

        Long clubMemberId = clubService.registerClub(clubId, (Long) session.getAttribute(USER_ID));
        log.info("ClubMemberId: {}", clubMemberId);

        return "redirect:/club/main?clubId=" + clubId;
    }

    @GetMapping("/member")
    public void goMember(@RequestParam Long clubId,
                         MemberPageRequestDTO requestDTO,
                         Model model,
                         HttpSession session) {
        log.info("GET /club/member?clubId={}", clubId);

        model.addAttribute(NAV_DTO, clubService.getNav(clubId, (Long) session.getAttribute(USER_ID)));
        model.addAttribute(RESULT, clubMemberService.getMemberPage(requestDTO));
    }


    // 가입 인사 페이지로 이동
    @GetMapping("/greetings")
    public void goGreetings(@RequestParam Long clubId,
                            @RequestParam(required = false) Long userId,
                            GreetingsPageRequestDTO requestDTO,
                            Model model,
                            HttpSession session) {
        log.info("GET /club/greetings?clubId={}&userId={}", clubId, userId);

        Long myUserId = (Long) session.getAttribute(USER_ID);
        Long myGreetingsId = greetingsService.getGreetings(clubId, myUserId).getGreetingsId();
        log.info("myGreetingsId: {}", myGreetingsId);

        requestDTO.setClubId(clubId);
        requestDTO.setUserId(userId);

        model.addAttribute("myGreetingsId", myGreetingsId);
        model.addAttribute(NAV_DTO, clubService.getNav(clubId, myUserId));
        model.addAttribute(RESULT, greetingsService.getGreetingsPage(requestDTO));
    }

    @GetMapping("/greetingsForm")
    public void goGreetingsForm(@RequestParam Long clubId,
                                @RequestParam(required = false) Long greetingsId,
                                Model model,
                                HttpSession session) {
        log.info("GET /club/greetingsForm?clubId={}&greetingsId={}", clubId, greetingsId);

        if (greetingsId != null) {
            String content = greetingsService.getGreetingsById(greetingsId).getContent();
            model.addAttribute("myContent", content);
            model.addAttribute("greetingsId", greetingsId);
        }
        model.addAttribute(NAV_DTO, clubService.getNav(clubId, (Long) session.getAttribute(USER_ID)));
        model.addAttribute("greetingsDTO", GreetingsDTO.builder().build());
    }

    @PostMapping("/createGreetings")
    public String createGreetings(@RequestParam Long clubId,
                                  @RequestParam String content,
                                  HttpSession session) {
        log.info("POST /club/createGreetings");

        Long clubMemberId = clubMemberService.getClubMemberId(clubId, (Long) session.getAttribute(USER_ID));
        Long greetingsId = greetingsService.createGreetings(clubMemberId, content);
        log.info("Greetings ID: {}", greetingsId);

        return "redirect:/club/greetings?clubId=" + clubId;
    }

    @PostMapping("/updateGreetings")
    public String updateGreetings(@RequestParam Long clubId,
                                  @RequestParam Long greetingsId,
                                  @RequestParam String content,
                                  Model model,
                                  HttpSession session) {
        log.info("POST /club/updateGreetings");
        greetingsService.updateGreetings(greetingsId, content);

        return "redirect:/club/greetings?clubId=" + clubId;
    }



    @GetMapping("/meeting")
    public void goMeeting(@RequestParam Long clubId, Model model) {
        log.info("GET /club/meeting");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
    }


    @GetMapping("/board")
    public void goBoard(@RequestParam Long clubId, Model model) {
        log.info("GET /club/board");

        model.addAttribute(NAV_DTO, clubService.getNav(0L, 0L));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////// 보류 라인

    // 1대1 문의 페이지로 이동
    @GetMapping("/inquiry")
    public void goInquiry(@RequestParam Long clubId, Model model, HttpSession session) {
        log.info("GET /club/inquiry/{}", clubId);

        model.addAttribute(NAV_DTO, clubService.getNav(clubId, (Long) session.getAttribute(USER_ID)));
    }

    @GetMapping("/calendar")
    public void goCalendar(@RequestParam Long clubId,
                           Model model,
                           HttpSession session) {
        log.info("GET /club/calendar");

        model.addAttribute(NAV_DTO, clubService.getNav(clubId, (Long) session.getAttribute(USER_ID)));
    }
}
