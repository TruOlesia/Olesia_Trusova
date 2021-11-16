package com.ot.conferences.service.repository;

import com.ot.conferences.service.model.User;

import java.util.List;

public interface UserRepository {

    User getUser(String login);

    List<User> listUsers();

    User createUser(User user);

    User updateUser(String login, User user);

    void deleteUser(String login);
}
