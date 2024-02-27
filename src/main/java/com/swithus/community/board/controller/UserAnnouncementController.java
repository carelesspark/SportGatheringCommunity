package com.swithus.community.board.controller;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.page.AncPageRequestDTO;
import com.swithus.community.manager.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class UserAnnouncementController {

    private final AnnouncementService service;

    @GetMapping("/announcement")
    public void announcement(AncPageRequestDTO pageRequestDTO, Model model){
        log.info("유저 뷰 공지사항 페이지");

        model.addAttribute("result", service.getAnnouncementList(pageRequestDTO));
    }

    @GetMapping("/announcement_info")
    public void info(long no, @ModelAttribute("pageRequestDTO") AncPageRequestDTO pageRequestDTO, Model model){
        log.info("공지사항 상세");

        AnnouncementDTO dto = service.info(no);
        model.addAttribute("dto", dto);
    }
}
