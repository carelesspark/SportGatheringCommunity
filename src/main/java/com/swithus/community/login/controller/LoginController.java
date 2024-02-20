package com.swithus.community.login.controller;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.service.LoginService;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.entity.AuthId;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/main")
    public String login(){

        return "login/login";
    }

    @PostMapping("/main")
    public String login(LoginDTO loginDTO) {
        //값 정상적으로 넘어옴//log.info(loginService.check(loginDTO)+"어느위치에서 출력인지 확인");
        Optional<AuthId> loginSuccess=loginService.check(loginDTO);
        if(!loginSuccess.isEmpty()){
            return "/main/main"; // 로그인 후 리다이렉트할 경로
        }
        return "/login/login";
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


