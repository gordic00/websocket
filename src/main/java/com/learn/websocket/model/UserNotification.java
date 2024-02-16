package com.learn.websocket.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserNotification {
    @Id
    private String id;
    private String senderId;
    private String recipientId;
    private Integer number = 0;
}
