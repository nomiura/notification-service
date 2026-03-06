package ru.test.notificationservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.test.notificationservice.event.Operation;
import ru.test.notificationservice.event.UserEvent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = "user-events")
public class NotificationIntegrationTest {

    @MockitoBean
    private JavaMailSender mailSender;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void testSendEmailFromKafka() {
        UserEvent event = new UserEvent();
        event.setEmail("testik@gmail.com");
        event.setState(Operation.CREATE);

        kafkaTemplate.send("user-events", event);
        verify(mailSender, timeout(3000)).send(any(SimpleMailMessage.class));
    }
}
