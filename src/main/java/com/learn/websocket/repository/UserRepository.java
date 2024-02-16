package com.learn.websocket.repository;

import com.learn.websocket.model.Status;
import com.learn.websocket.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);

    List<User> findAllByNickNameInOrderByStatusDesc(List<String> nickNames);

    List<User> findAllByNickNameNotInAndStatus(List<String> users, Status status);
}