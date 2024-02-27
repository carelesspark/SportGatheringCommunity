package com.swithus.community.board.controller;


import com.swithus.community.manager.dto.page.FaqPageRequestDTO;
import com.swithus.community.manager.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class UserFaqController {

    private final FaqService service;

    @GetMapping("/faq")
    public void faq_list(FaqPageRequestDTO pageRequestDTO, Model model){
        log.info("faq 페이지" + pageRequestDTO);

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("result", service.getFaqList(pageRequestDTO));
    }
}
