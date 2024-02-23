package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.InquiryAnswerDTO;
import com.swithus.community.manager.dto.InquiryDTO;
import com.swithus.community.manager.dto.page.MainPageRequestDTO;
import com.swithus.community.manager.service.InquiryService;
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
public class ManagerController {

    private final InquiryService inquiryService;

    @GetMapping("/main")
    public void home(){

    }


    @GetMapping("/main_modal")
    public void inquiry_modal(MainPageRequestDTO mainPageRequestDTO, Model model){
        log.info("메인 페이지 + inquiry" + mainPageRequestDTO);
        log.info(inquiryService.getInquiryList(mainPageRequestDTO).getDtoList());

        model.addAttribute("pageRequestDTO", mainPageRequestDTO);
        model.addAttribute("result", inquiryService.getInquiryList(mainPageRequestDTO));
    }

    @GetMapping("/inquiry_answer")
    public void inquiry_info(long no, long answerId, @ModelAttribute("pageRequestDTO")MainPageRequestDTO mainPageRequestDTO, Model model){
        log.info("inquiry_info");

        InquiryDTO dto = inquiryService.inquiryInfo(no);
        InquiryAnswerDTO dto2 = inquiryService.inquiryAnswerInfo(answerId);

        model.addAttribute("dto", dto);
        model.addAttribute("answer", dto2);
    }

    @PostMapping("/inquiry_answer_submit")
        public String inquiry_answer_submit(InquiryAnswerDTO inquiryAnswerDTO, @ModelAttribute("pageRequestDTO")MainPageRequestDTO mainPageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("inquiry 답장");

        Long id = inquiryService.inquiryAnswer(inquiryAnswerDTO);

        redirectAttributes.addFlashAttribute("answerId", id);
        redirectAttributes.addAttribute("no", inquiryAnswerDTO.getInquiryId());
        redirectAttributes.addAttribute("page", mainPageRequestDTO.getPage());

        return "redirect:/manager/inquiry_answer";
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
