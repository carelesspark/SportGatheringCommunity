package com.swithus.community.board.controller;

import com.swithus.community.board.dto.page.PageRequestDTO;
import com.swithus.community.board.service.PromotionBoardService;
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
public class PromotionController {

    private final PromotionBoardService promotionBoardService;

    @GetMapping("/promotion")
    public void promotion(PageRequestDTO pageRequestDTO, Model model){
        log.info("홍보 게시판 페이지");

        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("result", promotionBoardService.getPromotionList(pageRequestDTO));
    }


}
