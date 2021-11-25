package com.ot.conferences.service.impl;

import com.ot.conferences.model.User;
import com.ot.conferences.repository.UserRepository;
import com.ot.conferences.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUser(Long id) {
        log.info("getUser by id {}", id);
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
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
        log.info("createUser with id {}", user.getId());
        return userRepository.save(user);
    }


    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        log.info("deleteUser with id {}", id);
        userRepository.delete(user.orElse(null));
    }

}
