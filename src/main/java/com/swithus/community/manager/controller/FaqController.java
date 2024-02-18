package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.FaqDTO;
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

        log.info("공지사항 작성");
        Long id = service.write(dto);

        redirectAttributes.addFlashAttribute("id", id);

        return "redirect:/manager/faq";
    }

    @GetMapping("/faq_info")
    @ResponseBody
    public ResponseEntity<FaqDTO> getFAQInfo(@RequestParam long no) {
        FaqDTO faqInfo = service.info(no);
        return new ResponseEntity<>(faqInfo, HttpStatus.OK);
    }


}
