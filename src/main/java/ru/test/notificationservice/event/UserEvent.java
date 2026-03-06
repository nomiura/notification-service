package ru.test.notificationservice.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class UserEvent {
    private String email;
    private Operation state;
}
