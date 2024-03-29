package com.swithus.community.board.controller;

import com.swithus.community.board.dto.BoardInquiryDTO;
import com.swithus.community.manager.repository.InquiryRepository;
import com.swithus.community.manager.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping("/inquiry")
    public String inquiry(Model model, HttpSession session ){
        String nickname = (String) session.getAttribute("userNickname");
        if(nickname == null){
            model.addAttribute("msg", "로그인이 필요한 페이지입니다.");
            model.addAttribute("nickname", nickname);
            return "/board/return";
        } else {
            log.info(nickname);
            model.addAttribute("nickname", nickname);
            return "/board/inquiry";
        }
    }

    @PostMapping("/inquiry_post")
    public String post(BoardInquiryDTO boardInquiryDTO, RedirectAttributes redirectAttributes){

        inquiryService.postInquiry(boardInquiryDTO);

        return "redirect:/swithus/main";
    }
}
