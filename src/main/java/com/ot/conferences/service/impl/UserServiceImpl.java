package com.ot.conferences.service.impl;
import com.ot.conferences.service.UserService;
import com.ot.conferences.model.User;
import com.ot.conferences.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String login) {
        log.info("getUser by login {}", login);
        User user = userRepository.getUser(login);
        return user;
    }

    @Override
    public List<User> listUsers() {
        log.info("get all users");
        return userRepository.listUsers();
    }

    @Override
    public User createUser(User user) {
        log.info("createUser with login {}", user.getLogin());
        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(String login, User user) {
        log.info("updateUser with login {}", login);
        return userRepository.updateUser(login, user);
    }

    @Override
    public void deleteUser(String login) {
        log.info("deleteUser with login {}", login);
        userRepository.deleteUser(login);
    }

}
