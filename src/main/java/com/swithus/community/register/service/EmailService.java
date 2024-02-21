package com.swithus.community.register.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    String generateVerificationCode();

    void sendVerificationCode(String email);

    boolean verifyCode(String email, String code);
}
