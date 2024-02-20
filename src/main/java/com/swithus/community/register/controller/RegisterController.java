package com.swithus.community.register.controller;

import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> join(UserDTO userDTO) {
        try {
            registerService.join(userDTO);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (DuplicateFormatFlagsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 아이디입니다.");
        }
    }

    @GetMapping("/checkUserId/{userId}")
    @ResponseBody
    public ResponseEntity<String> checkUserId(@PathVariable String userId) {
        boolean isUserIdExists = registerService.isUserIdExists(userId);
        return ResponseEntity.ok(isUserIdExists ? "EXIST" : "AVAILABLE");
    }

}
