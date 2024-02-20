package com.swithus.community.register.controller;

import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Log4j2
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/main")
    public String main(){

        return "register/main";
    }
    @GetMapping("/agree")
    public String agree(){

        return "register/agree";
    }

    @GetMapping("/registerform")
    public String registerform() {

        return "register/registerForm";
    }

    @PostMapping("/registerform")
    public String join(UserDTO userDTO) {
        Long userId = registerService.join(userDTO);

        return "/main/main";
    }


}
