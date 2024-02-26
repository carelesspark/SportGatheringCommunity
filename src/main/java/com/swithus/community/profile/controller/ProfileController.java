package com.swithus.community.profile.controller;

import com.swithus.community.profile.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
@Log4j2
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    @GetMapping("/main")
    public String main(HttpSession session, Model model){

        String userId = (String) session.getAttribute("RuserId");
        String userNickname = (String) session.getAttribute("userNicknname");
        String userEmail = (String) session.getAttribute("userEmail");
        String userAddrDetail = (String) session.getAttribute("userAddrDetail");
        String userPost = (String) session.getAttribute("userPost");

        model.addAttribute("userId",userId);
        model.addAttribute("userNickname", userNickname);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userAddrDetail",userAddrDetail);
        model.addAttribute("userPost",userPost);

        return "profile/profile";
    }

    @PostMapping("/editNickname")
    @ResponseBody
    public String editNickname(@RequestParam String newNickname, HttpSession session) {
        String userId = (String) session.getAttribute("RuserId");

        try {
            // 세션에서 받은 userId를 Long으로 변환
            Long userIdLong = Long.parseLong(userId);

            // 새로운 닉네임이 이미 존재하는지 확인
            if (profileService.isNicknameExists(newNickname)) {
                return "DUPLICATE"; // 중복된 닉네임인 경우
            }

            // 중복이 아니라면 닉네임 업데이트
            profileService.updateNickname(userIdLong, newNickname);
            session.setAttribute("userNickname", newNickname);

            return "SUCCESS";
        } catch (NumberFormatException e) {
            // userId가 올바른 Long 형태가 아닌 경우 처리
            return "INVALID_USER_ID";
        }
    }


}
