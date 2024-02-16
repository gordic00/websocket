package com.learn.websocket.service;

import com.learn.websocket.model.UserNotification;
import com.learn.websocket.repository.UserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNotificationService {
    private final UserNotificationRepository repo;

    public void save(String senderId, String recipientId) {
        UserNotification userNotification = getOrUpdate(senderId, recipientId);
        userNotification.setSenderId(senderId);
        userNotification.setRecipientId(recipientId);
        userNotification.setNumber(userNotification.getNumber() + 1);
        repo.save(userNotification);
    }

    private UserNotification getOrUpdate(String senderId, String recipientId) {
        UserNotification userNotification = repo.findBySenderIdAndRecipientId(senderId, recipientId);
        return userNotification != null ? userNotification : new UserNotification();
    }

    public void delete(String senderId, String recipientId) {
        repo.deleteAllBySenderIdAndRecipientId(senderId, recipientId);
    }

    public List<UserNotification> getNotificationUsers(String recipientId) {
        return repo.findAllByRecipientId(recipientId);
    }
}
