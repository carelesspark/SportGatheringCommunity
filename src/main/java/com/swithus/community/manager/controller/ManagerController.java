package com.swithus.community.manager.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
@Log4j2
@RequiredArgsConstructor
public class ManagerController {
    @GetMapping("/main")
    public String home(){

        return "manager/managerMain";
    }

    @GetMapping("/announcement")
    public String announcement(){

        return "manager/announcement";
    }

    @GetMapping("/announcement_info")
    public String announcement_info(){

        return "manager/announcement_info";
    }
}
