package com.swithus.community.manager.controller;

import com.swithus.community.club.dto.ClubDTO;
import com.swithus.community.club.service.ClubService;
import com.swithus.community.manager.dto.GatheringDTO;
import com.swithus.community.manager.dto.WithdrawalGatheringDTO;
import com.swithus.community.manager.dto.WithdrawalUserDTO;
import com.swithus.community.manager.dto.page.GatheringPageRequestDTO;
import com.swithus.community.manager.dto.page.UserPageRequestDTO;
import com.swithus.community.manager.service.GatheringService;
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
@Log4j2
@RequestMapping("/manager")
@RequiredArgsConstructor
public class GatheringController {

    private final GatheringService gatheringService;
    private final ClubService clubService;

    @GetMapping("/gathering")
    public void gathering_list(GatheringPageRequestDTO gatheringPageRequestDTO, Model model){

        log.info("Gathering 관리 페이지" + gatheringPageRequestDTO);

        model.addAttribute("pageRequestDTO", gatheringPageRequestDTO);
        model.addAttribute("result", gatheringService.getGatheringList(gatheringPageRequestDTO));
    }

    @GetMapping("/gathering_info")
    public void info(long no, @ModelAttribute("pageRequestDTO")GatheringPageRequestDTO gatheringPageRequestDTO, Model model){

        log.info("Gathering 상세 페이지");

        GatheringDTO gatheringDTO = gatheringService.info(no);
        ClubDTO clubDTO = clubService.getClub(no);

        model.addAttribute("dto", gatheringDTO);
        model.addAttribute("dto2", clubDTO);
    }

    @PostMapping("/withdrawal_gathering")
    public String withdrawal(WithdrawalGatheringDTO withdrawalGatheringDTO, @ModelAttribute("pageRequestDTO") GatheringPageRequestDTO gatheringPageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("Gathering 탈퇴 적용");

        gatheringService.withdrawalGathering(withdrawalGatheringDTO);
        redirectAttributes.addAttribute("page", gatheringPageRequestDTO.getPage());

        return "redirect:/manager/gathering";
    }

    @GetMapping("/deleted_gathering")
    public void deleted_gathering_list(GatheringPageRequestDTO gatheringPageRequestDTO, Model model){
        log.info("삭제된 Gathering 관리 페이지" + gatheringPageRequestDTO);

        model.addAttribute("pageRequestDTO", gatheringPageRequestDTO);
        model.addAttribute("result", gatheringService.getDeletedGatheringList(gatheringPageRequestDTO));
    }

    @GetMapping("/deleted_gathering_reason")
    public void deleted_gathering_detail(long no, @ModelAttribute("pageRequestDTO") GatheringPageRequestDTO gatheringPageRequestDTO, Model model){
        log.info("삭제된 Gathering 상세 정보");

        WithdrawalGatheringDTO dto = gatheringService.infoDeletedGathering(no);

        model.addAttribute("dto", dto);

    }
}
