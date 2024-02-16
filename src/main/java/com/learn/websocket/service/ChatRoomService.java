package com.learn.websocket.service;

import com.learn.websocket.model.ChatRoom;
import com.learn.websocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository repo;

    public Optional<String> getChatRoomId(
            String senderId, String recipientId, boolean createNewRoomIfNotExist
    ) {
        return repo.findBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getChatId).or(
                () -> {
                    if (createNewRoomIfNotExist) {
                        String chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }
                    return Optional.empty();
                });

    }

    private String createChatId(String senderId, String recipientId) {
        String chatId = String.format("%s_%s", senderId, recipientId);
        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();
        ChatRoom recipientSender = ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        repo.save(senderRecipient);
        repo.save(recipientSender);
        return chatId;
    }
}
