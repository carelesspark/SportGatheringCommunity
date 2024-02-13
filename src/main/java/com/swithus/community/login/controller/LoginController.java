package com.swithus.community.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/login")
@Log4j2
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/main")
    public String announcement(){

        return "login/login";
    }
}
