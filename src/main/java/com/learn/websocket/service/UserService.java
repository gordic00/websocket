package com.learn.websocket.service;

import com.learn.websocket.model.ChatMessage;
import com.learn.websocket.model.Status;
import com.learn.websocket.model.User;
import com.learn.websocket.repository.ChatMessageRepository;
import com.learn.websocket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final ChatMessageRepository chatMessageRepo;

    public void saveUser(User user) {
        user.setStatus(Status.ONLINE);
        repo.save(user);
    }

    public void disconnect(User user) {
        User storeUser = repo.findById(user.getNickName()).orElse(null);
        if (storeUser != null) {
            storeUser.setStatus(Status.OFFLINE);
            repo.save(storeUser);
        }
    }

    public List<User> findConnectedUsers(String userId) {
        List<ChatMessage> messages = chatMessageRepo.findAllBySenderIdOrRecipientId(userId, userId);
        List<String> users = messages.stream().map(m -> m.getRecipientId().equals(userId) ? m.getSenderId() : m.getRecipientId()).distinct().toList();
        List<User> response = repo.findAllByNickNameInOrderByStatusDesc(users);
        //TODO add new endpoint to get list of users by nickname or name contains and delete this under.
        response.addAll(repo.findAllByNickNameNotInAndStatus(users, Status.ONLINE));
        return response;
    }
}
