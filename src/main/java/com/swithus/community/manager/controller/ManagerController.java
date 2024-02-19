package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.PageRequestDTO;
import com.swithus.community.manager.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
@Log4j2
@RequiredArgsConstructor
public class ManagerController {

    @GetMapping("/main")
    public void home(){

    }


    @GetMapping("/faq")
    public String faq(){

        return "manager/faq";
    }

    @GetMapping("/user")
    public String user(){

        return "manager/user";
    }

    @GetMapping("/gathering")
    public String gathering(){

        return "manager/gathering";
    }

    @GetMapping("/report_comment")
    public String report_comment(){

        return "manager/report_comment";
    }

    @GetMapping("/report_comment_info")
    public String report_comment_info(){

        return "manager/report_comment_info";
    }

    @GetMapping("/report_post")
    public String report_post(){

        return "manager/report_post";
    }

    @GetMapping("/report_post_info")
    public String report_post_info(){

        return "manager/report_post_info";
    }
}
