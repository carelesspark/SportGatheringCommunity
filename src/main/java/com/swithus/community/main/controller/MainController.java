package com.swithus.community.main.controller;

import com.swithus.community.board.entity.Promotion;
import com.swithus.community.board.repository.PromotionBoardRepository;
import com.swithus.community.board.service.PromotionBoardService;
import com.swithus.community.manager.dto.AnnouncementDTO;
import com.swithus.community.manager.entity.Announcement;
import com.swithus.community.manager.service.AnnouncementService;
import com.swithus.community.manager.service.MainImageService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/swithus")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    private final AnnouncementService announcementService;
    private final PromotionBoardService promotionBoardService;

    @GetMapping("/main")
    public String announcement(HttpSession session, Model model){
        // 세션에서 사용자 정보를 가져옴
        String RuserId= (String) session.getAttribute("RuserId");
        String nickname= (String) session.getAttribute("userNickname");

        log.info(RuserId + " + " + nickname);

        //String userName = (String) session.getAttribute("userName");
         //세션에 사용자 정보가 없으면 로그인 페이지로 이동
        if (RuserId == null) {

            return "redirect:/login/login";
        }else{
             //모델에 userId와 userName을 추가
            model.addAttribute("RuserId", RuserId);
            model.addAttribute("nickname", nickname);

            List<Announcement> result = announcementService.findTop4ByOrderByRegDateDesc();
            log.info(result);
            List<Promotion> result2 = promotionBoardService.findTop4ByOrderByRegDateDesc();
            log.info(result2);
            model.addAttribute("announcement", result);
            model.addAttribute("promotion", result2);

            //model.addAttribute("userName", userName);
            return "main/main";
        }
//        // 세션에 사용자 정보가 있으면 메인 페이지로 이동
    }


}
