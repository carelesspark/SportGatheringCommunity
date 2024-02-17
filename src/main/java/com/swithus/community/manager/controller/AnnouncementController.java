package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.PageRequestDTO;
import com.swithus.community.manager.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public void info(long no, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model){
        log.info("공지사항 상세");

        AnnouncementDTO dto = service.info(no);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/announcement_modify")
    public String modify(AnnouncementDTO dto, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("공지사항 수정");

        service.modify(dto);

        redirectAttributes.addAttribute("no", dto.getId());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/manager/announcement_info";
    }

    @PostMapping("/announcement_delete")
    public String delete(long id, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("공지사항 삭제");

        service.delete(id);

        redirectAttributes.addFlashAttribute("no", id);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());


        return "redirect:/manager/announcement";
    }

}
