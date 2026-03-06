package ru.test.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendCreatedEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("my_email@gmail.com");
        message.setTo(email);
        message.setSubject("Аккаунт создан");
        message.setText("Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
        mailSender.send(message);
    }

    public void sendDeletedEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("my_email@gmail.com");
        message.setTo(email);
        message.setSubject("Аккаунт удален");
        message.setText("Здравствуйте! Ваш аккаунт был удален.");
        mailSender.send(message);
    }
}
