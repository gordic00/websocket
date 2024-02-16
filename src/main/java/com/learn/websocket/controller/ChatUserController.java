package com.learn.websocket.controller;

import com.learn.websocket.model.User;
import com.learn.websocket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatUserController {
    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo({"/user/public"})
    public User addUser(
            @Payload User user
    ) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnectUser(
            @Payload User user
    ) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<User>> findConnectedUsers(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok(userService.findConnectedUsers(userId));
    }
}
