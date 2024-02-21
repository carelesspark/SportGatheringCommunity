package com.swithus.community.register.service.impl;

import com.swithus.community.register.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    private final Map<String, String> verificationCodes = new HashMap<>();
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public String generateVerificationCode() {
        // Generate a random 6-digit verification code
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    @Override
    public void sendVerificationCode(String email) {
        String verificationCode = generateVerificationCode();
        String subject = "이메일 인증 코드";
        String text = "인증 코드: " + verificationCode;

        // Save the verification code for later validation
        verificationCodes.put(email, verificationCode);

        // Send the verification code via email
        sendSimpleMessage(email, subject, text);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        // Retrieve the verification code for the given email
        String storedCode = verificationCodes.get(email);

        // Check if the provided code matches the stored one
        return storedCode != null && storedCode.equals(code);
    }
}
