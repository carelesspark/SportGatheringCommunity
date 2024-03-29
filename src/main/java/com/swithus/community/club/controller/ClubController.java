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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private final ClubPostCtgrService clubPostCtgrService;

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

    @GetMapping("/waitingList")
    public void goWaitingList(@RequestParam Long clubId,
                              Model model,
                              HttpSession session) {
        log.info("GET /club/waitingList?clubId={}", clubId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);
        List<ClubMemberDTO> waitingList = clubMemberService.getWaitingList(clubId);
        log.info("대기자 인원수: {}", waitingList);

        model.addAttribute("navDTO", navDTO);
        if (waitingList.isEmpty()) {
            model.addAttribute("waitingList", null);
        } else {
            model.addAttribute("waitingList", waitingList);
        }
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam Long clubId,
                          @RequestParam Long clubMemberId) {
        log.info("GET /club/welcome?clubId={}&clubMemberId={}", clubId, clubMemberId);

        clubService.welcome(clubMemberId);

        return "redirect:/club/waitingList?clubId=" + clubId;
    }

    @GetMapping("/deny")
    public String deny(@RequestParam Long clubId,
                       @RequestParam Long clubMemberId) {
        log.info("GET /club/deny?clubId={}&clubMemberId={}", clubId, clubMemberId);

        clubService.withdrawClub(clubMemberId);

        return "redirect:/club/waitingList?clubId=" + clubId;
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
            model.addAttribute("meetingDTO", null);
        } else {
            log.info("모임 수정");
            isEditing = true;
            model.addAttribute("meetingDTO", meetingService.getMeetingDTO(meetingId));
        }

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("servletPath", request.getServletPath());
        model.addAttribute("isEditing", isEditing);
        model.addAttribute("ctgrList", meetingCtgrService.getCtgrList());
    }

    @GetMapping("/deleteMeeting")
    public String deleteMeeting(@RequestParam Long clubId,
                                @RequestParam Long meetingId) {
        log.info("POST /club/deleteMeeting");

        meetingService.deleteMeeting(meetingId);

        return "redirect:/club/meeting?clubId=" + clubId;
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
        model.addAttribute("ctgrList", clubPostCtgrService.getCtgrList());
        model.addAttribute("selectedCtgrId", ctgrId);
        model.addAttribute("selectedType", type);
        model.addAttribute("selectedKeyword", keyword);
        model.addAttribute("result", clubPostService.getClubPostDTOPage(pageRequestDTO));
    }

    @GetMapping("/postForm")
    public void goPostForm(@RequestParam Long clubId,
                           @RequestParam(required = false) Long postId,
                           Model model,
                           HttpSession session,
                           HttpServletRequest request) {
        log.info("GET /club/postForm?clubId={}&postId={}", clubId, postId);

        Long userId = (Long) session.getAttribute("userId");
        Long clubMemberId = clubMemberService.getClubMemberId(clubId, userId);
        NavDTO navDTO = clubService.getNavDTO(clubId, clubMemberId);

        boolean isEditing;
        if (postId == null) {
            log.info("게시글 생성");
            isEditing = false;
            model.addAttribute("postDTO", null);
        } else {
            log.info("게시글 수정");
            isEditing = true;
            model.addAttribute("postDTO", clubPostService.getClubPostDTO(postId));
        }

        model.addAttribute("navDTO", navDTO);
        model.addAttribute("servletPath", request.getServletPath());
        model.addAttribute("isEditing", isEditing);
        model.addAttribute("ctgrList", clubPostCtgrService.getCtgrList());
    }

    @PostMapping("/createPost")
    public String createPost(ClubPostDTO clubPostDTO) {
        log.info("POST /club/createPost");

        clubPostDTO.setVisitCount(0);
        Long postId = clubPostService.createPost(clubPostDTO);

        return "redirect:/club/post?clubId=" + clubPostDTO.getClubId() + "&postId=" + postId;
    }

    @PostMapping("/updatePost")
    public String updatePost(@RequestParam Long clubId,
                             @RequestParam Long postId,
                             @ModelAttribute ClubPostDTO clubPostDTO) {
        log.info("POST /club/updatePost");

        clubPostDTO.setClubPostId(postId);
        clubPostDTO.setClubId(clubId);
        clubPostService.updatePost(clubPostDTO);

        return "redirect:/club/post?clubId=" + clubPostDTO.getClubId() + "&postId=" + clubPostDTO.getClubPostId();
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam Long clubId,
                             @RequestParam Long postId) {
        log.info("POST /club/deletePost");

        clubPostService.deleteClubPost(postId);

        return "redirect:/club/board?clubId=" + clubId;
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

    @GetMapping("/withdraw")
    public String withdrawClub(@RequestParam Long clubMemberId) {
        log.info("GET /club/withdraw?clubMemberId={}", clubMemberId);

        clubService.withdrawClub(clubMemberId);

        return "redirect:/club/search";
    }

    @GetMapping("/delete")
    public String deleteClub(@RequestParam Long clubId) {
        log.info("GET /club/post?clubId={}", clubId);

        clubService.deleteClub(clubId);

        return "redirect:/club/search";
    }
}
