package com.ot.conferences.service;

import com.ot.conferences.model.User;

import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserService {

    public User getUser(Long id);

    List<User> listUsers(Pageable paging);

    User save(User user);

    void deleteUser(Long id);

}
