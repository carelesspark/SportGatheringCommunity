package com.swithus.community.board.controller;

import com.swithus.community.board.dto.PromotionBoardDTO;
import com.swithus.community.board.dto.ReportPostDTO;
import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.service.PromotionBoardService;
import com.swithus.community.board.service.PromotionReportService;
import com.swithus.community.club.entity.Club;
import com.swithus.community.club.repository.ClubRepository;
import com.swithus.community.club.service.ClubService;
import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.dto.page.AncPageRequestDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionBoardService promotionBoardService;
    private final ClubService clubService;
    private final PromotionReportService promotionReportService;

    @GetMapping("/promotion")
    public void promotion(PageRequestDTO pageRequestDTO, Model model) {
        log.info("홍보 게시판 페이지");

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("result", promotionBoardService.getPromotionList(pageRequestDTO));
    }

    @GetMapping("/promotion_info")
    public void info(long no, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model, HttpSession session) {
        log.info("홍보 게시판 상세");

        String nickname = (String) session.getAttribute("userNickname");

        PromotionBoardDTO dto = promotionBoardService.info(no);
        log.info(dto);
        model.addAttribute("nickname", nickname);
        model.addAttribute("dto", dto);
    }

    @GetMapping("/promotion_write")
    public String write(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model, HttpSession session) {
        log.info("홍보 게시판 상세");

        String nickname = (String) session.getAttribute("userNickname");

        boolean result = clubService.checkHaveClub(nickname);
        log.info(result);

        if (result) {
            List<Club> clubList = clubService.findUsersClub(nickname);
            log.info(clubList);
            model.addAttribute("nickname", nickname);
            model.addAttribute("list", clubList);
            return "/board/promotion_write";
        } else {
            model.addAttribute("msg", "클럽을 보유한 유저만 작성 가능한 페이지입니다.");
            model.addAttribute("nickname", nickname);
            return "/board/return";
        }
    }

    @PostMapping("/promotion_write_post")
    public String postWrite(PromotionBoardDTO promotionBoardDTO, RedirectAttributes redirectAttributes) {
        log.info("홍보 게시판 작성");

        promotionBoardService.write(promotionBoardDTO);

        return "redirect:/board/promotion";
    }

    @PostMapping("/promotion_modify")
    public String modify(PromotionBoardDTO dto, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {

        log.info("홍보 게시판 수정");

        promotionBoardService.modify(dto);

        redirectAttributes.addAttribute("no", dto.getId());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("search", pageRequestDTO.getSearch());

        return "redirect:/board/promotion_info";
    }

    @PostMapping("/promotion_report")
    public String report(ReportPostDTO reportPostDTO, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes, HttpSession session, Model model){
        log.info("홍보 게시글 신고");

        String nickname = (String) session.getAttribute("userNickname");

        if (nickname == null){
            model.addAttribute("msg", "신고 기능은 유저만 가능합니다.");
            model.addAttribute("nickname", nickname);
            return "/board/return";
        }

        Long isReported = promotionReportService.count(reportPostDTO);

        if(isReported > 0){
            model.addAttribute("msg", "이미 신고하신 게시글입니다.");
            model.addAttribute("nickname", nickname);
            model.addAttribute("no", reportPostDTO.getPostId());
            model.addAttribute("page", pageRequestDTO.getPage());
            model.addAttribute("type", pageRequestDTO.getType());
            model.addAttribute("search", pageRequestDTO.getSearch());
            return "/board/return2";
        }
        promotionReportService.report(reportPostDTO);

        redirectAttributes.addAttribute("no", reportPostDTO.getPostId());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("search", pageRequestDTO.getSearch());

        return "redirect:/board/promotion_info";
    }

    @PostMapping("/promotion_delete")
    public String delete(long id, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes) {
        log.info("홍보 게시글 삭제");


        promotionBoardService.deletePromotion(id);

        redirectAttributes.addFlashAttribute("no", id);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("search", pageRequestDTO.getSearch());


        return "redirect:/board/promotion";
    }
}
