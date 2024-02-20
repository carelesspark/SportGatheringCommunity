package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.page.AncPageRequestDTO;
import com.swithus.community.manager.dto.page.UserPageRequestDTO;
import com.swithus.community.manager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manager")
@Log4j2
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/user")
    public void user(UserPageRequestDTO userPageRequestDTO, Model model){

        log.info("Uesr 관리 페이지");

        model.addAttribute("result", service.getUserList(userPageRequestDTO));
    }

    @GetMapping("/user_info")
    public String info(){

        return "/manager/user_info";
    }


}
