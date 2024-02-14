package com.swithus.community.register.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Log4j2
@RequiredArgsConstructor
public class RegisterController {

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
}
