package com.swithus.community.board.announcement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    @GetMapping("/announcement/list")
    public void list(){
        log.info("list");
    }

    @GetMapping("/announcement/view")
    public void announcementView(){
        log.info("view");
    }

    @GetMapping("/announcement/modify")
    public void announcementModify() {
        log.info("zz");
    }
}
