package com.swithus.community.main.controller;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/swithus")
@Log4j2
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/main")
    public String announcement(HttpSession session, Model model){
        // 세션에서 사용자 정보를 가져옴
        String userid = (String) session.getAttribute("userid");
        String username = (String) session.getAttribute("username");
        log.info("session userid: "+userid+""+" username: "+username);
        // 세션에 사용자 정보가 없으면 로그인 페이지로 이동
//        if (userid == null || username == null) {
//
//            return "redirect:/login/login";
//        }else{
            // 모델에 userid와 username을 추가
//            model.addAttribute("userid", userid);
//            model.addAttribute("username", username);
            return "main/main";
//        }
//        // 세션에 사용자 정보가 있으면 메인 페이지로 이동
    }



}
