package com.swithus.community.profile.controller;

import com.swithus.community.global.exception.DuplicateFormatException;
import com.swithus.community.profile.service.MemberService;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.DuplicateFormatFlagsException;

@Controller
@RequestMapping("/profile")
@Log4j2
@RequiredArgsConstructor
public class ProfileController {
    private final MemberService memberService;
    private final RegisterService registerService;
    @GetMapping("/main")
    public String main(HttpSession session, Model model, RedirectAttributes redirectAttributes){
        // 세션에서 로그인 정보 가져오기
        String userId = (String) session.getAttribute("RuserId");
        String userNickname = (String) session.getAttribute("userNickname");

        if (userId == null || userNickname == null) {
            // 로그인이 되어있지 않은 경우
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 되어있지 않습니다. 로그인을 해주세요.");
            return "redirect:/profile/confirmAlert"; // 확인 창을 띄우는 페이지로 리다이렉트
        }

        String userEmail = (String) session.getAttribute("userEmail");
        String userAddr = (String) session.getAttribute("userAddr");
        String userAddrDetail = (String) session.getAttribute("userAddrDetail");
        String userPost = (String) session.getAttribute("userPost");
        String userGender = (String) session.getAttribute("userGender");

        model.addAttribute("userId",userId);
        model.addAttribute("userNickname", userNickname);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userAddr",userAddr);
        model.addAttribute("userAddrDetail",userAddrDetail);
        model.addAttribute("userPost",userPost);
        model.addAttribute("userGender",userGender);

        return "profile/profile";
    }

    @GetMapping("/confirmAlert")
    public String confirmalert(){
        return "profile/confirmAlert";
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateProfile(HttpSession session,@RequestBody  UserDTO updatedUser, RedirectAttributes redirectAttributes) {
        try {
            Long userId = (Long) session.getAttribute("userId");
            updatedUser.setId(userId);
            log.info("userEMAIL: {}",updatedUser.getEmail());

            //////////////////////////
            session.setAttribute("userNickname", updatedUser.getNickname());
            session.setAttribute("userEmail", updatedUser.getEmail());
            session.setAttribute("userAddr", updatedUser.getAddr());
            session.setAttribute("userAddrDetail", updatedUser.getAddrDetail());
            session.setAttribute("userPost", updatedUser.getPost());
            //////////////////////////////////
            log.info("updatedUser: {}",updatedUser);

            memberService.updateUserInfo(updatedUser);

            // 업데이트 성공 시 메시지를 리다이렉트로 전달
            redirectAttributes.addFlashAttribute("successMessage", "프로필이 업데이트되었습니다.");

        } catch (Exception e) {
            log.error("프로필 업데이트 중 오류 발생: " + e.getMessage());

            // 업데이트 실패 시 메시지를 리다이렉트로 전달
            redirectAttributes.addFlashAttribute("errorMessage", "프로필 업데이트에 실패했습니다. 다시 시도해주세요.");
        }
//        catch (DuplicateFormatException e) {
//            // 중복 에러가 발생한 경우 사용자에게 메시지 전달
//            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//            return "redirect:/profile/profile";
//        }

        return "redirect:/profile/main";
    }

    @GetMapping("/checkUserNickname/{userNickname}")
    @ResponseBody
    public ResponseEntity<String> checkUserNickname(@PathVariable String userNickname) {
        boolean isUserNicknameExists = registerService.isUserNicknameExists(userNickname);
        return ResponseEntity.ok(isUserNicknameExists ? "EXIST" : "AVAILABLE");
    }

    @GetMapping("/checkUserEmail/{userEmail}")
    @ResponseBody
    public ResponseEntity<String> checkUserEmail(@PathVariable String userEmail) {
        boolean isUserEmailExists = registerService.isUserEmailExists(userEmail);
        return ResponseEntity.ok(isUserEmailExists ? "EXIST" : "AVAILABLE");
    }

}