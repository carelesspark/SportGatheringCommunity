package com.swithus.community.email.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    Boolean sendEmail(String toEmail) throws MessagingException;
    Boolean verifyEmailCode(String email, String code);
}
