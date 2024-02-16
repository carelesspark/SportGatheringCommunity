package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.PageRequestDTO;
import com.swithus.community.manager.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manager")
@Log4j2
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService service;

    @GetMapping("/announcement")
    public void announcement(PageRequestDTO pageRequestDTO, Model model){
        log.info("공지사항 페이지");

        model.addAttribute("result", service.getAnnouncementList(pageRequestDTO));
    }

    @PostMapping("/announcement_write")
    public String write(AnnouncementDTO dto, RedirectAttributes redirectAttributes){

        log.info("공지사항 작성");
        Long id = service.write(dto);

        redirectAttributes.addFlashAttribute("id", id);

        return "redirect:/manager/announcement";
    }

    @GetMapping("/announcement_info")
    public String announcement_info(){

        return "manager/announcement_info";
    }

}
