package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.GatheringDTO;
import com.swithus.community.manager.dto.page.GatheringPageRequestDTO;
import com.swithus.community.manager.service.GatheringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/manager")
@RequiredArgsConstructor
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping("/gathering")
    public void gathering_list(GatheringPageRequestDTO gatheringPageRequestDTO, Model model){

        log.info("Gathering 관리 페이지" + gatheringPageRequestDTO);

        model.addAttribute("pageRequestDTO", gatheringPageRequestDTO);
        model.addAttribute("result", gatheringService.getGatheringList(gatheringPageRequestDTO));
    }

    @GetMapping("/gathering_info")
    public void info(long no, @ModelAttribute("pageRequestDTO")GatheringPageRequestDTO gatheringPageRequestDTO, Model model){

        log.info("Gathering 상세 페이지");

        GatheringDTO dto = gatheringService.info(no);
        model.addAttribute("dto", dto);
    }
}
