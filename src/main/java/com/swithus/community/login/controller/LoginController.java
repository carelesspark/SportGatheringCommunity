package com.swithus.community.login.controller;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.service.LoginService;
import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.AuthId;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/login")
@Log4j2
@RequiredArgsConstructor
public class LoginController {


    private final LoginService loginService;

    @GetMapping("/login")
    public String login(){

        return "login/login";
    }

@PostMapping("/login")
public String login(LoginDTO loginDTO, HttpSession session) {
    Optional<AuthId> loginSuccess = loginService.check(loginDTO);
    AuthId value = loginSuccess.orElse(null); // orElse를 통해 Optional 값을 가져옵니다.
    if (value != null) {
        //R은 Regular(일반회원)으로 나타내려고 붙였습니다.
        session.setAttribute("RuserId", value.getUserid());
        //session.setAttribute("userName", value.getUser().getName());
        // 클럽쪽에서 사용할 것들 추가했습니다.
        session.setAttribute("userId",value.getUser().getId());
        session.setAttribute("userName", value.getUser().getName());

        //프로필페이지 사용할것
        session.setAttribute("userNickname", value.getUser().getNickname());
        session.setAttribute("userEmail", value.getUser().getEmail());
        session.setAttribute("userAddrDetail", value.getUser().getAddrDetail());
        session.setAttribute("userPost", value.getUser().getPost());

        // 관리자 페이지로 점핑하기 위한 코드입니다.
        if(value.getUser().getId()== 1L){
            return "redirect:/manager/main";
        }
        return "redirect:/swithus/main";//redirect할 땐 처음 들어가지는 경로로 지정해야 올바른 경로로 바뀌어서 감
    }
    return "login/login";
}

@GetMapping("/logout")
public String logout(HttpSession session){
        session.invalidate();

        return "redirect:/swithus/main";
}


    @GetMapping("/findId")
    public String findId(){

        return "login/findId";
    }

    @PostMapping("/findId")
    public ResponseEntity<Map<String, String>> mailSend(UserDTO userDTO) throws MessagingException {
        log.info("EmailController.mailSend()");

        // 이메일 및 이름을 통해 아이디를 찾는 서비스 호출
        Optional<String> foundUserIdOptional = loginService.findUserId(userDTO.getEmail(), userDTO.getName());

        Map<String, String> response = new HashMap<>();
        if (foundUserIdOptional.isPresent()) {
            response.put("status", "success");
            response.put("userId", foundUserIdOptional.get());  // 아이디를 클라이언트에게 전달
        } else {
            response.put("status", "fail");
            response.put("message", foundUserIdOptional.orElse("이메일 인증이 완료되지 않았습니다."));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findPwd")
    public String findPwd(){

        return "login/findPwd";
    }

    @GetMapping("/changePwd")
    public String changePwd(){

        return "login/changePwd";
    }



}


