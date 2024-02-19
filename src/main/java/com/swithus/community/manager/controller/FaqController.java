package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.FaqDTO;
import com.swithus.community.manager.dto.page.AncPageRequestDTO;
import com.swithus.community.manager.dto.page.FaqPageRequestDTO;
import com.swithus.community.manager.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manager")
@Log4j2
@RequiredArgsConstructor
public class FaqController {

    private final FaqService service;

    @GetMapping("/faq")
    public void faq(FaqPageRequestDTO pageRequestDTO, Model model){
        log.info("faq 페이지");

        model.addAttribute("result", service.getFaqList(pageRequestDTO));
    }

    @PostMapping("/faq_write")
    public String write(FaqDTO dto, RedirectAttributes redirectAttributes){

        log.info("faq 작성");
        Long id = service.write(dto);

        redirectAttributes.addFlashAttribute("id", id);

        return "redirect:/manager/faq";
    }

    @GetMapping("/faq_info")
    public void info(long no, @ModelAttribute("pageRequestDTO") FaqPageRequestDTO pageRequestDTO, Model model) {
        FaqDTO dto = service.info(no);
        model.addAttribute("info", dto);
    }

    @PostMapping("/faq_modify")
    public String modify(FaqDTO dto, @ModelAttribute("pageRequestDTO") FaqPageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("faq 수정");

        service.modify(dto);

        redirectAttributes.addAttribute("no", dto.getId());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/manager/faq_info";
    }

    @PostMapping("/faq_delete")
    public String delete(long id, @ModelAttribute("pageRequestDTO") AncPageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("faq 삭제");

        service.delete(id);

        redirectAttributes.addFlashAttribute("no", id);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());


        return "redirect:/manager/faq";
    }
}
