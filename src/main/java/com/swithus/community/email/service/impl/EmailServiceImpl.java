package com.swithus.community.email.service.impl;

import com.swithus.community.email.service.EmailService;
import com.swithus.community.global.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;
    @Autowired
    private final RedisUtil redisUtil;
    private static final String senderEmail = "Auth@swithus.com";
    @Override
    public Boolean sendEmail(String toEmail) throws MessagingException {
        if (redisUtil.existData(toEmail)) {
            redisUtil.deleteData(toEmail);
        }

        try {
            // 이메일 폼 생성
            MimeMessage emailForm = createEmailForm(toEmail);
            // 이메일 발송
            javaMailSender.send(emailForm);

            return true;  // 이 부분을 수정하여 성공 여부를 true로 반환합니다.

        } catch (MessagingException e) {
            // 이메일 발송 중 예외 발생 시
            log.error("이메일 발송 중 오류 발생: {}", e.getMessage());
            return false;  // 발송 실패 시 false를 반환합니다.
        }
    }

    @Override
    public Boolean verifyEmailCode(String email, String code) {
        String codeFoundByEmail = redisUtil.getData(email);
        log.info("이메일로 찾은 코드: " + codeFoundByEmail);
        if (codeFoundByEmail == null) {
            return false;
        }
        return codeFoundByEmail.equals(code);
    }

    private String createCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 | i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // 이메일 내용 초기화
    private String setContext(String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code);

        templateResolver.setPrefix("templates/mail/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("mail", context);
    }

    // 이메일 폼 생성
    private MimeMessage createEmailForm(String email) throws MessagingException {
        String authCode = createCode();

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("안녕하세요. 스윗어스입니다.");
        message.setFrom(senderEmail);
        message.setText(setContext(authCode), "utf-8", "html");

        // Redis 에 해당 인증코드 인증 시간 설정
        redisUtil.setDataExpire(email, authCode, 60 * 30L);

        return message;
    }
}