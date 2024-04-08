package com.swithus.community.manager.controller;

import com.swithus.community.board.dto.ReportPostDTO;
import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.service.PromotionBoardService;
import com.swithus.community.board.service.PromotionReportService;
import com.swithus.community.manager.dto.page.ReportedPageRequestDTO;
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
public class ReportController {

    private final PromotionReportService promotionReportService;

    @GetMapping("/report_post")
    public void report_list(ReportedPageRequestDTO dto, Model model){
        log.info("신고된 게시글 페이지");

        model.addAttribute("result", promotionReportService.getReportedPostList(dto));
    }

    @GetMapping("/report_post_info")
    public void report_info(Long no, @ModelAttribute("pageRequestDTO") ReportedPageRequestDTO pageRequestDTO, Model model){
        log.info("신고된 게시글 상세 페이지");


        ReportPostDTO dto = promotionReportService.info(no);
        Long reportedCount = promotionReportService.countReportedPost(dto.getPostId());

        model.addAttribute("dto", dto);
        model.addAttribute("reportedCount", reportedCount);
    }

    @PostMapping("/reported_promotion_delete")
    public String delete(long postId, long id, @ModelAttribute("pageRequestDTO") ReportedPageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("신고된 홍보 게시글 삭제");

        promotionReportService.deletePromotion(postId, id);

        redirectAttributes.addAttribute("no", id);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/manager/report_post_info";
    }

    @PostMapping("/reported_promotion_isUnsuitabled")
    public String unsuitabled(long id, @ModelAttribute("pageRequestDTO") ReportedPageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("신고에 부적합한 게시글 버튼 클릭");

        promotionReportService.isUnsuitabled(id);

        redirectAttributes.addAttribute("no", id);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/manager/report_post_info";
    }

}
