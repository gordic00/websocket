package com.learn.websocket.repository;

import com.learn.websocket.model.UserNotification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends MongoRepository<UserNotification, String> {
    void deleteAllBySenderIdAndRecipientId(String senderId, String recipientId);

    List<UserNotification> findAllByRecipientId(String recipientId);

    UserNotification findBySenderIdAndRecipientId(String senderId, String recipientId);
}
