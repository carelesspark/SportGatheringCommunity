package com.swithus.community.main.controller;

import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.service.PromotionBoardService;
import com.swithus.community.club.service.ClubService;
import com.swithus.community.main.dto.PopularClubDTO;
import com.swithus.community.manager.entity.Announcement;
import com.swithus.community.manager.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/swithus")
@Log4j2
@RequiredArgsConstructor
public class MainController {
    private final ClubService clubService;
    private final AnnouncementService announcementService;
    private final PromotionBoardService promotionBoardService;

    @GetMapping("/main")
    public String announcement(Model model) {
        log.info("/swithus/main");

        List<Announcement> result = announcementService.findTop4ByOrderByRegDateDesc();
        log.info(result);
        List<Promotion> result2 = promotionBoardService.findTop4ByOrderByRegDateDesc();
        log.info(result2);

        List<PopularClubDTO> popularClubDTOList = clubService.getPopularClubDTOList(4);
        log.info(popularClubDTOList);

        if (ObjectUtils.isEmpty(popularClubDTOList)) {
            log.warn("Club이 존재하지 않습니다.");
            model.addAttribute("popularClubDTOList", null);
        } else {
            log.warn("Club이 존재합니다.");
            model.addAttribute("popularClubDTOList", popularClubDTOList);
        }

        model.addAttribute("announcement", result);
        model.addAttribute("promotion", result2);
        return "main/main";
    }
}