package com.ot.conferences.repository;

import com.ot.conferences.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User getByLogin(String login);
}
