package com.learn.websocket.controller;

import com.learn.websocket.model.UserNotification;
import com.learn.websocket.service.UserNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserNotificationController {
    private final UserNotificationService service;

    @GetMapping("/notification-users/{userId}")
    public ResponseEntity<List<UserNotification>> getNotificationUsers(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(service.getNotificationUsers(userId));
    }
}
