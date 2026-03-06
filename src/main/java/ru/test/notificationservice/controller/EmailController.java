package ru.test.notificationservice.controller;

import ru.test.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/created")
    public void sendCreated(@RequestParam String email) {
        emailService.sendCreatedEmail(email);
    }

    @PostMapping("/deleted")
    public void sendDeleted(@RequestParam String email) {
        emailService.sendDeletedEmail(email);
    }
}
