package com.ot.conferences.service.impl;

import com.ot.conferences.model.User;
import com.ot.conferences.repository.UserRepository;
import com.ot.conferences.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUser(String login) {
        log.info("getUser by login {}", login);
        User user = userRepository.getByLogin(login);
        return user;
    }

    @Override
    public List<User> listUsers(Pageable paging) {
        Page<User> pagedResult = userRepository.findAll(paging);
        log.info("get all users");

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }
    }

    @Override
    public User save(User user) {
        log.info("createUser with login {}", user.getLogin());
        return userRepository.save(user);
    }


    @Override
    public void deleteUser(String login) {
        User user = userRepository.getByLogin(login);
        log.info("deleteUser with login {}", login);
        userRepository.delete(user);
    }

}
