package com.swithus.community.login.controller;

import com.swithus.community.login.dto.LoginDTO;
import com.swithus.community.login.service.LoginService;
import com.swithus.community.register.service.RegisterService;
import com.swithus.community.user.entity.AuthId;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

//    @PostMapping("/login")
//    public String login(LoginDTO loginDTO) {
//        //값 정상적으로 넘어옴//log.info(loginService.check(loginDTO)+"어느위치에서 출력인지 확인");
//        Optional<AuthId> loginSuccess = loginService.check(loginDTO);
//       // AuthId value = loginSuccess.get();
//        log.info(loginSuccess);//Optional[AuthId{id=4, user=User(id=4, name=testjb, nickname=testjb, email=lskdjf@naver.com, birth=15556666, gender=male, addr=null, addrDetail=null, post=null, isLeader=0), userid='testjb', userpwd='1234'}]
//        //log.info(value);
//        if(!loginSuccess.isEmpty()){
////            session.setAttribute("userid", value.getUserid());//userId세션 저장
////            session.setAttribute("username", value.getUser().getName());//userId세션 저장
//
////            String userid = (String) session.getAttribute("userid");
////            String username = (String) session.getAttribute("username");
//
//            //log.info("After setting session - userid: {}, username: {}", userid, username);
//            //setAttribute잘 작동함
//            return "main/main"; // 로그인 후 리다이렉트할 경로
//        }
//        return "login/login";
//    }
@PostMapping("/login")
public String login(LoginDTO loginDTO, HttpSession session) {
    Optional<AuthId> loginSuccess = loginService.check(loginDTO);
    AuthId value = loginSuccess.orElse(null); // orElse를 통해 Optional 값을 가져옵니다.
    if (value != null) {
        session.setAttribute("userid", value.getUserid());
        session.setAttribute("username", value.getUser().getName());
        // 클럽쪽에서 사용할 것들 추가했습니다.
        session.setAttribute("userId",value.getUser().getId());
        session.setAttribute("userName", value.getUser().getName());
        // 관리자 페이지로 점핑하기 위한 코드입니다.
        if(value.getUser().getId()== 1L){
            return "redirect:/manager/main";
        }
        return "redirect:/swithus/main";//redirect할 땐 처음 들어가지는 경로로 올바른 경로로 바뀌어서 감
    }
    return "login/login";
}




    @GetMapping("/findId")
    public String findId(){

        return "login/findId";
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


