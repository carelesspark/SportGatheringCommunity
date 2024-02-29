package com.swithus.community.register.controller;

import com.swithus.community.global.exception.InvalidBirthException;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.DuplicateFormatFlagsException;

@Controller
@RequestMapping("/register")
@Log4j2
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/main")
    public String main(){

        return "register/main";
    }
    @GetMapping("/agree")
    public String agree(){

        return "register/agree";
    }

    @GetMapping("/registerform")
    public String registerform() {

        return "register/registerForm";
    }

    @PostMapping("/registerform")
    public String join(UserDTO userDTO, RedirectAttributes redirectAttributes) {
        try {
            registerService.join(userDTO);
            return "redirect:/register/regSuccess";
        } catch (DuplicateFormatFlagsException e) {
            // 중복 에러가 발생한 경우 사용자에게 메시지 전달
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register/registerform";
        } catch (IllegalArgumentException e) {
            // 이메일 인증 코드 오류 예외
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register/registerform";
        } catch (InvalidBirthException e) {
            // 생년월일 예외가 발생한 경우 사용자에게 메시지 전달
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register/registerform";
        }

    }

    @GetMapping("/regSuccess")
    public String regSuccess(){
        return "register/regSuccess";
    }
    @GetMapping("/checkUserId/{userId}")
    @ResponseBody
    public ResponseEntity<String> checkUserId(@PathVariable String userId) {
        boolean isUserIdExists = registerService.isUserIdExists(userId);
        return ResponseEntity.ok(isUserIdExists ? "EXIST" : "AVAILABLE");
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
