package com.swithus.community.login.controller;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.service.LoginService;
import com.swithus.community.user.dto.UserDTO;
import com.swithus.community.user.entity.AuthId;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(Model model){
        // 로그인 페이지로 이동할 때 전달된 errorMessage를 가져와서 alert 창을 띄움
        String errorMessage = (String) model.getAttribute("errorMessage");
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("showAlert", true);
        }

        return "login/login";
    }

//
@PostMapping("/login")
public String login(LoginDTO loginDTO, HttpSession session, Model model) {
    Optional<AuthId> loginSuccess = loginService.check(loginDTO);

    if (!loginSuccess.isPresent()) {
        model.addAttribute("loginError", "아이디나 비밀번호가 올바르지 않습니다.");

        return "login/login"; // 로그인 실패 시 로그인 페이지에 머무르도록 리턴
    }

    AuthId value = loginSuccess.get(); // 이 부분에서는 isPresent()를 통해 확인 후 사용

    // 아래는 로그인 성공 시의 로직
    session.setAttribute("RuserId", value.getUserid());
    session.setAttribute("userId", value.getUser().getId());
    session.setAttribute("userName", value.getUser().getName());
    session.setAttribute("userNickname", value.getUser().getNickname());

    session.setAttribute("userNickname", value.getUser().getNickname());
    session.setAttribute("userEmail", value.getUser().getEmail());
    session.setAttribute("userAddrDetail", value.getUser().getAddrDetail());
    session.setAttribute("userPost", value.getUser().getPost());
    session.setAttribute("userAddr", value.getUser().getAddr());

    if (value.getUser().getId() == 1L) {
        return "redirect:/manager/main";
    }

    return "redirect:/swithus/main";
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

    @GetMapping("/findPwd")
    public String findPwd(){

        return "login/findPwd";
    }

    @PostMapping("/findId")
    public ResponseEntity<Map<String, String>> findId(UserDTO userDTO) throws MessagingException {
        log.info("EmailController.mailSend()");
        log.info("userDTO.getVerificationCode() :" + userDTO.getVerificationCode());
        // 이메일 및 이름을 통해 아이디를 찾는 서비스 호출
        Optional<String> foundUserIdOptional = loginService.findUserId(userDTO.getEmail(), userDTO.getName(), userDTO.getVerificationCode());
        log.info("foundUserIdOptional = " + foundUserIdOptional);

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

    @PostMapping("/findPwd")
    public ResponseEntity<Map<String, String>> findPwd(UserDTO userDTO) throws MessagingException {
        log.info("EmailController.mailSend()");
        log.info("userDTO.getVerificationCode() :" + userDTO.getVerificationCode());
        // 이메일 및 아이디를 통해 비밀번호를 찾는 서비스 호출
        Optional<String> foundUserPwdOptional = loginService.findUserPwd(userDTO.getEmail(), userDTO.getUserid(), userDTO.getVerificationCode());
        log.info("foundUserPwdOptional = " + foundUserPwdOptional);

        Map<String, String> response = new HashMap<>();
        if (foundUserPwdOptional.isPresent()) {
            response.put("status", "success");
            response.put("userPwd", foundUserPwdOptional.get());  // 아이디를 클라이언트에게 전달
        } else {
            response.put("status", "fail");
            response.put("message", foundUserPwdOptional.orElse("이메일 인증이 완료되지 않았습니다."));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/updatePwd")
    public String changePwd(){

        return "login/updatePwd";
    }

    @PostMapping("/updatePwd")
    public ResponseEntity<Map<String, String>> updatePwd(UserDTO userDTO, @RequestParam String newPwd) throws MessagingException {
        log.info("userid: " + userDTO.getUserid());
        log.info("newPwd: " + newPwd);

        try {
            loginService.updatePwd(userDTO.getUserid(), newPwd);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "비밀번호가 성공적으로 재설정되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("비밀번호 재설정 중 오류 발생", e);
            Map<String, String> response = new HashMap<>();
            response.put("status", "fail");
            response.put("message", "비밀번호 재설정 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}


