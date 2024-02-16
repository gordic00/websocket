package com.learn.websocket.repository;

import com.learn.websocket.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);

    List<ChatMessage> findAllBySenderIdOrRecipientId(String senderId, String recipientId);
}
