package com.swithus.community.register.controller;

import com.swithus.community.register.service.EmailService;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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
    private final EmailService emailService;

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

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendSimpleMessage(to, subject, text);
        return ResponseEntity.ok("이메일 전송 성공!");
    }

    @PostMapping("/sendVerificationCode")
    @ResponseBody
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        try {
            // Send verification code to the provided email
            emailService.sendVerificationCode(email);
            return ResponseEntity.ok("이메일로 인증 코드가 전송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 전송 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        try {
            // Verify the provided code for the given email
            boolean isValid = emailService.verifyCode(email, code);
            if (isValid) {
                return ResponseEntity.ok("인증 코드가 유효합니다.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 인증 코드입니다.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("인증 코드 검증 중 오류가 발생했습니다.");
        }
    }

}
