package com.swithus.community.board.controller;

import com.swithus.community.board.dto.PromotionBoardDTO;
import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.service.PromotionBoardService;
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

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class PromotionController {

    private final PromotionBoardService promotionBoardService;

    @GetMapping("/promotion")
    public void promotion(PageRequestDTO pageRequestDTO, Model model){
        log.info("홍보 게시판 페이지");

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("result", promotionBoardService.getPromotionList(pageRequestDTO));
    }

    @GetMapping("/promotion_info")
    public void info(long no, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model, HttpSession session){
        log.info("홍보 게시판 상세");

        String nickname = (String) session.getAttribute("userNickname");

        PromotionBoardDTO dto = promotionBoardService.info(no);
        model.addAttribute("nickname", nickname);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/promotion_modify")
    public String modify(PromotionBoardDTO dto, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){

        log.info("홍보 게시판 수정");

        promotionBoardService.modify(dto);

        redirectAttributes.addAttribute("no", dto.getId());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("search", pageRequestDTO.getSearch());

        return "redirect:/board/promotion_info";
    }

    @PostMapping("/promotion_delete")
    public String delete(long id, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("홍보 게시글 삭제");


        promotionBoardService.deletePromotion(id);

        redirectAttributes.addFlashAttribute("no", id);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type", pageRequestDTO.getType());
        redirectAttributes.addAttribute("search", pageRequestDTO.getSearch());


        return "redirect:/board/promotion";
    }
}
