package com.ot.conferences.repository.impl;

import com.ot.conferences.model.User;
import com.ot.conferences.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final List<User> list = new ArrayList<>();

    @Override
    public User getUser(String login) {
        return list.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> listUsers() {
        return new ArrayList<>(list);
    }

    @Override
    public User createUser(User user) {
        list.add(user);
        return user;
    }

    @Override
    public User updateUser(String login, User user) {
        boolean isDeleted = list.removeIf(u -> u.getLogin().equals(login));
        if (isDeleted) {
            list.add(user);
        } else {
            return null;
        }
        return user;
    }

    @Override
    public void deleteUser(String login) {
        list.removeIf(user -> user.getLogin().equals(login));
    }
}
