package com.swithus.community.email;

import com.swithus.community.email.service.EmailService;
import com.swithus.community.user.dto.UserDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/verify-email")
    public ResponseEntity<Map<String, String>> mailSend(UserDTO userDTO) throws MessagingException {
        log.info("EmailController.mailSend()");
        boolean isSend = emailService.sendEmail(userDTO.getEmail());

        Map<String,String> response = new HashMap<>();
        if (isSend) {
            response.put("status","success");
        } else {
            response.put("status", "fail");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verification-code")
    public ResponseEntity<Map<String, String>> verify(UserDTO userDTO) {
        log.info("EmailController.verify()");
        boolean isVerify = emailService.verifyEmailCode(userDTO.getEmail(), userDTO.getVerifyCode());

        Map<String,String> response = new HashMap<>();
        if (isVerify) {
            response.put("status","success");
        } else {
            response.put("status", "fail");
        }

        return ResponseEntity.ok(response);
    }
}
