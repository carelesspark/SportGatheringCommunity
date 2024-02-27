package com.swithus.community.profile.controller;

import com.swithus.community.profile.service.MemberService;
import com.swithus.community.user.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/profile")
@Log4j2
@RequiredArgsConstructor
public class ProfileController {
    private final MemberService memberService;
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

    @PostMapping("/update")
    public String updateProfile(HttpSession session, UserDTO updatedUser, RedirectAttributes redirectAttributes) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            updatedUser.setId(userId);

            memberService.updateUserInfo(updatedUser);

            // 업데이트 성공 시 메시지를 리다이렉트로 전달
            redirectAttributes.addFlashAttribute("successMessage", "프로필이 업데이트되었습니다.");

        } catch (Exception e) {
            log.error("프로필 업데이트 중 오류 발생: " + e.getMessage());

            // 업데이트 실패 시 메시지를 리다이렉트로 전달
            redirectAttributes.addFlashAttribute("errorMessage", "프로필 업데이트에 실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/profile/main";
    }

}
