package com.example.novelcharacter.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendEmail(String toEmail,String title, String content) throws javax.mail.MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail); // 메일 수신자
        helper.setSubject(title); // 메일 제목
        helper.setText(content, true); // content는 메일 본문 내용, true를 설정해서 HTML을 사용 가능하게 함
        helper.setReplyTo("no-reply@domain.com"); // 회신 불가능한 주소 설정
        try {
            emailSender.send(message);
        } catch (RuntimeException e) {
            e.printStackTrace(); // 또는 로거를 사용하여 상세한 예외 정보 로깅
            throw new RuntimeException("Unable to send email in sendEmail", e); // 원인 예외를 포함시키기, 나중에 커스텀 예외 생성해서 관리하는게 좋음
        }
    }

    public SimpleMailMessage createEmailForm(String toEmail, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);
        return message;
    }


}
