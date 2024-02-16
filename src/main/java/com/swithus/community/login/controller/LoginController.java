package com.swithus.community.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@Log4j2
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String login(){

        return "login/login";
    }

    @PostMapping("/main")
    public String login(@RequestParam String email, @RequestParam String password) {
        // 여기에 Spring Security를 사용한 로그인 처리 로직 추가
        // 예: AuthenticationManager를 사용하여 로그인 시도

        return "/main/main"; // 로그인 후 리다이렉트할 경로
    }


    @GetMapping("/findId")
    public String findId(){

        return "login/findId";
    }

    @GetMapping("/findPwd")
    public String findPwd(){

        return "login/findPwd";
    }

    @GetMapping("/changePwd")
    public String changePwd(){

        return "login/changePwd";
    }



}
