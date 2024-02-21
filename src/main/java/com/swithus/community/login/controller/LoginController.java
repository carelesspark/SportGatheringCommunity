package com.swithus.community.login.controller;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.service.LoginService;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.entity.AuthId;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/login")
    public String login(){

        return "login/login";
    }


@PostMapping("/login")
public String login(LoginDTO loginDTO, HttpSession session) {
    Optional<AuthId> loginSuccess = loginService.check(loginDTO);
    AuthId value = loginSuccess.orElse(null); // orElse를 통해 Optional 값을 가져옵니다.
    if (value != null) {
        session.setAttribute("userId", value.getUserid());
        session.setAttribute("userName", value.getUser().getName());
        return "redirect:/swithus/main";//redirect할 땐 처음 들어가지는 경로로 올바른 경로로 바뀌어서 감
    }
    return "login/login";
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


