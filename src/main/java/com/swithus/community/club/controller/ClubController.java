package com.swithus.community.club.controller;

import com.swithus.community.club.dto.*;
import com.swithus.community.club.dto.page.ClubPostPageRequestDTO;
import com.swithus.community.club.dto.page.GreetingsPageRequestDTO;
import com.swithus.community.club.dto.page.MemberPageRequestDTO;
import com.swithus.community.club.dto.page.SearchPageRequestDTO;
import com.swithus.community.club.service.*;
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

import java.time.LocalDateTime;

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
    private final MeetingService meetingService;
    private final MeetingCtgrService meetingCtgrService;
    private final ClubPostService clubPostService;

    // 클럽 서칭 페이지로 이동
    @GetMapping("/search")
    public void search(SearchPageRequestDTO pageRequestDTO,
                       Model model) {
        log.info("GET /club/search");

        Long regionId = pageRequestDTO.getRegionId();
        Long sportsId = pageRequestDTO.getSportsId();
        String keyword = pageRequestDTO.getKeyword();
        log.info("region: {}, sports: {},keyword: {}", regionId, sportsId, keyword);

        model.addAttribute("selectedRegionId", regionId);
        model.addAttribute("selectedSportsId", sportsId);
        model.addAttribute("searchKeyword", keyword);
        model.addAttribute("regionList", regionService.getRegionList());
        model.addAttribute("sportsList", sportsService.getSportsList());
        model.addAttribute("result", clubService.getSearchPage(pageRequestDTO));
    }

    // 클럽 생성 페이지로 이동
    @GetMapping("/create")
    public void goCreate(Model model,
                         HttpServletRequest request) {
        log.info("GET /club/create");

        // 경로 알려줘서 해당 css 파일 적용하기
        model.addAttribute("servletPath", request.getServletPath());
        model.addAttribute("regionList", regionService.getRegionList());
        model.addAttribute("sportsList", sportsService.getSportsList());
    }

    // 클럽 생성 및 해당 클럽으로 이동
    @PostMapping("/createClub")
    public String createClub(ClubDTO clubDTO,
                             HttpSession session) {
        log.info("POST /club/createClub");
        clubDTO.setRank(0); // 둘 다 처음엔 0
        clubDTO.setPoint(0); // 둘 다 처음엔 0
        clubDTO.setLeaderId((Long) session.getAttribute("userId"));

        Long clubId = clubService.create(clubDTO);
        log.info("Club ID: {}", clubId);

        return "redirect:/club/main?clubId=" + clubId;
    }

    // 클럽 메인 페이지 이동
    @GetMapping("/main")
    public void goMain(@RequestParam Long clubId,
                       Model model,
                       HttpSession session) {
        log.info("GET /club/main?clubId={}", clubId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        log.info("clubMemberId: {}", clubMemberId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("clubDTO", clubService.getClub(clubId));
    }

    // 가입 요청 기능 구현
    @GetMapping("/register")
    public String doRegister(@RequestParam Long clubId,
                             HttpSession session) {
        log.info("GET /club/register?clubId={}", clubId);

        Long userId = (Long) session.getAttribute("userId");

        Long clubMemberId = clubService.registerClub(clubId, userId);
        log.info("ClubMemberId: {}", clubMemberId);

        return "redirect:/club/main?clubId=" + clubId;
    }

    @GetMapping("nicknameForm")
    public void goNicknameForm(@RequestParam Long clubId,
                               @RequestParam Long clubMemberId,
                               Model model) {
        log.info("GET /club/nicknameForm?clubId={}&clubMemberId={}", clubId, clubMemberId);

        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);
        model.addAttribute("navDTO", navDTO);
    }

    @PostMapping("changeNickname")
    public String changeNickname(@RequestParam Long clubId,
                                 NavDTO navDTO) {
        log.info("POST /club/changeNickname");

        Long clubMemberId = navDTO.getClubMemberId();
        String nickname = navDTO.getNickname();

        clubMemberService.changeNickname(clubMemberId, nickname);

        return "redirect:/club/main?clubId=" + clubId;
    }

    @GetMapping("/member")
    public void goMember(@RequestParam Long clubId,
                         MemberPageRequestDTO requestDTO,
                         Model model,
                         HttpSession session) {
        log.info("GET /club/member?clubId={}", clubId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("result", clubMemberService.getMemberPage(requestDTO));
    }

    // 가입 인사 페이지로 이동
    @GetMapping("/greetings")
    public void goGreetings(@RequestParam Long clubId,
                            @RequestParam(required = false) Long userId,
                            GreetingsPageRequestDTO requestDTO,
                            Model model,
                            HttpSession session) {
        log.info("GET /club/greetings?clubId={}&userId={}", clubId, userId);

        Long sessionUserId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, sessionUserId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        Long myGreetingsId = greetingsService.getGreetings(clubId, sessionUserId).getGreetingsId();
        log.info("myGreetingsId: {}", myGreetingsId);

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("checkUserId", userId);
        model.addAttribute("myGreetingsId", myGreetingsId);
        model.addAttribute("result", greetingsService.getGreetingsDTOPage(requestDTO));
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

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("greetingsDTO", GreetingsDTO.builder().build());
    }

    @PostMapping("/createGreetings")
    public String createGreetings(@RequestParam Long clubId,
                                  @RequestParam String content,
                                  HttpSession session) {
        log.info("POST /club/createGreetings");

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        Long greetingsId = greetingsService.createGreetings(clubMemberId, content);
        log.info("Greetings ID: {}", greetingsId);

        return "redirect:/club/greetings?clubId=" + clubId;
    }

    @PostMapping("/updateGreetings")
    public String updateGreetings(@RequestParam Long clubId,
                                  @RequestParam Long greetingsId,
                                  @RequestParam String content) {
        log.info("POST /club/updateGreetings");
        greetingsService.updateGreetings(greetingsId, content);

        return "redirect:/club/greetings?clubId=" + clubId;
    }

    @GetMapping("/meeting")
    public void goMeeting(@RequestParam Long clubId,
                          @RequestParam(required = false) Long ctgrId,
                          Model model,
                          HttpSession session) {
        log.info("GET /club/meeting?clubId={}", clubId);

        LocalDateTime now = LocalDateTime.now();

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("currentCtgrId", ctgrId);
        model.addAttribute("meetingDTOList", meetingService.getActiveMeetingDTOList(clubId, ctgrId, now));
    }

    @GetMapping("/meetingForm")
    public void goMeetingForm(@RequestParam Long clubId,
                              @RequestParam(required = false) Long meetingId,
                              Model model,
                              HttpSession session,
                              HttpServletRequest request) {
        log.info("GET /club/meetingForm?clubId={}", clubId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        boolean isEditing;
        if (meetingId == null) {
            log.info("모임 생성");
            isEditing = false;
        } else {
            log.info("모임 수정");
            isEditing = true;
        }

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("servletPath", request.getServletPath());
        model.addAttribute("isEditing", isEditing);
        model.addAttribute("ctgrList", meetingCtgrService.getCtgrList());
    }

    @PostMapping("/createMeeting")
    public String createMeeting(@RequestParam Long clubId,
                                MeetingDTO meetingDTO) {
        log.info("POST /club/createMeeting");

        meetingService.createMeeting(meetingDTO);

        return "redirect:/club/meeting?clubId=" + clubId;
    }

    @PostMapping("/updateMeeting")
    public String updateMeeting(@RequestParam Long clubId,
                                @RequestParam Long meetingId,
                                MeetingDTO meetingDTO) {
        log.info("POST /club/updateMeeting");

        meetingService.updateMeeting(meetingDTO);

        return "redirect:/club/meeting?clubId=" + clubId;
    }

    @GetMapping("/meetingDetail")
    public void goMeetingDetail(@RequestParam Long clubId,
                                @RequestParam Long meetingId,
                                Model model,
                                HttpSession session) {
        log.info("GET /club/goMeetingDetail?clubId={}&meetingId={}", clubId, meetingId);


        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        boolean isAttended = meetingService.existsMeetingMember(meetingId, clubMemberId);

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("isAttended", isAttended);
        model.addAttribute("meetingDTO", meetingService.getMeetingDTO(meetingId));
    }

    @GetMapping("/attendMeeting")
    public String attendMeeting(@RequestParam Long clubId,
                                @RequestParam Long meetingId,
                                @RequestParam boolean isAttended,
                                HttpSession session
    ) {
        log.info("GET /club/goMeetingDetail?clubId={}&meetingId={}&isAttended={}", clubId, meetingId, isAttended);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);

        if (isAttended) {
            meetingService.deleteMeetingMember(meetingId, clubMemberId);
        } else {
            meetingService.insertMeetingMember(meetingId, clubMemberId);
        }

        return "redirect:/club/meetingDetail?clubId=" + clubId + "&meetingId=" + meetingId;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 수정중

    @GetMapping("/board")
    public void goBoard(@RequestParam Long clubId,
                        ClubPostPageRequestDTO pageRequestDTO,
                        Model model,
                        HttpSession session) {
        log.info("GET /club/board?clubId={}", clubId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        Long ctgrId = pageRequestDTO.getCtgrId();
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("selectedCtgrId", ctgrId);
        model.addAttribute("selectedType", type);
        model.addAttribute("selectedKeyword", keyword);
        model.addAttribute("result", clubPostService.getClubPostDTOPage(pageRequestDTO));
    }

    @GetMapping("/post")
    public void goPost(@RequestParam Long clubId,
                       @RequestParam Long postId,
                       Model model,
                       HttpSession session) {
        log.info("GET /club/post?clubId={}&postId={}", clubId, postId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        ClubPostDTO postDTO = clubPostService.getClubPostDTO(postId);

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("postDTO", postDTO);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////// 보류 라인


    // 1대1 문의 페이지로 이동
    @GetMapping("/inquiry")
    public void goInquiry(@RequestParam Long clubId, Model model, HttpSession session) {
        log.info("GET /club/inquiry/{}", clubId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        model.addAttribute("navDTO", navDTO);
    }

    @GetMapping("/calendar")
    public void goCalendar(@RequestParam Long clubId,
                           Model model,
                           HttpSession session) {
        log.info("GET /club/calendar");

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        model.addAttribute("navDTO", navDTO);
    }
}
