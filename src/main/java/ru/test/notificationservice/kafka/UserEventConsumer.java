package ru.test.notificationservice.kafka;

import ru.test.notificationservice.event.Operation;
import ru.test.notificationservice.event.UserEvent;
import ru.test.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserEventConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "${topic.user-events}")
    public void consume(UserEvent event) {
        if (event.getState().equals(Operation.CREATE)) {
            emailService.sendCreatedEmail(event.getEmail());
        }

        if (event.getState().equals(Operation.DELETE)) {
            emailService.sendDeletedEmail(event.getEmail());
        }
    }
}
