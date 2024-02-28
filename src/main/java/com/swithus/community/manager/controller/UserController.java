package com.swithus.community.manager.controller;

import com.swithus.community.manager.dto.FaqDTO;
import com.swithus.community.manager.dto.UserDTO;
import com.swithus.community.manager.dto.WithdrawalUserDTO;
import com.swithus.community.manager.dto.page.AncPageRequestDTO;
import com.swithus.community.manager.dto.page.UserPageRequestDTO;
import com.swithus.community.manager.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Service;
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
public class UserController {

    private final UserService service;

    @GetMapping("/user")
    public void user_list(UserPageRequestDTO userPageRequestDTO, Model model){

        log.info("Uesr 관리 페이지" + userPageRequestDTO);

        model.addAttribute("pageRequestDTO", userPageRequestDTO);
        model.addAttribute("result", service.getUserList(userPageRequestDTO));
    }

    @GetMapping("/user_info")
    public void info(long no, @ModelAttribute("pageRequestDTO") UserPageRequestDTO userPageRequestDTO, Model model){

        log.info("User 상세 페이지");
        UserDTO dto = service.info(no);

        model.addAttribute("dto", dto);
    }

    @PostMapping("/withdrawal_user")
    public String withdrawal(WithdrawalUserDTO withdrawalUserDTO, @ModelAttribute("pageRequestDTO")UserPageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        
        log.info("User 탈퇴 적용");

        service.withdrawalUser(withdrawalUserDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/manager/user";
    }

    @GetMapping("/deleted_user")
    public void deleted_user_list(UserPageRequestDTO userPageRequestDTO, Model model){

        log.info("삭제된 Uesr 관리 페이지" + userPageRequestDTO);

        model.addAttribute("pageRequestDTO", userPageRequestDTO);
        model.addAttribute("result", service.getDeletedUserList(userPageRequestDTO));
    }

    @GetMapping("/deleted_user_reason")
    public void deleted_user_detail(long no, @ModelAttribute("pageRequestDTO")UserPageRequestDTO userPageRequestDTO, Model model){
        log.info("삭제된 User 상세 정보");

        WithdrawalUserDTO dto = service.infoDeletedUser(no);
        model.addAttribute("dto", dto);
    }

}
