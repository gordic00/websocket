package com.learn.websocket.service;

import com.learn.websocket.model.ChatMessage;
import com.learn.websocket.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repo;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        String chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true).orElseThrow();
        chatMessage.setChatId(chatId);
        return repo.save(chatMessage);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        String chatId = chatRoomService.getChatRoomId(senderId, recipientId, false).orElse(null);
        return chatId != null ? repo.findByChatId(chatId) : new ArrayList<>();
    }

}
