package com.ot.conferences.repository;

import com.ot.conferences.model.User;

import java.util.List;

public interface UserRepository {

    User getUser(String login);

    List<User> listUsers();

    User createUser(User user);

    User updateUser(String login, User user);

    void deleteUser(String login);
}
