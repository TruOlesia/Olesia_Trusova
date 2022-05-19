package com.ot.conferences.service.impl;

import com.ot.conferences.model.User;
import com.ot.conferences.model.UserDetailsPrincipal;
import com.ot.conferences.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.getByLogin(s);
        log.info(user.getLogin()+ " " + user.getPassword() + " " + user.getFullName() +" " + user.getRole());
        if (user==null){
            log.info("user login null");
            throw new UsernameNotFoundException("Cannot find user:" + s);
        }

        return new UserDetailsPrincipal(user);
    }


}