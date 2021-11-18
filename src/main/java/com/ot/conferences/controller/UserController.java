package com.ot.conferences.controller;
import com.ot.conferences.controller.dto.UserDto;
import com.ot.conferences.service.UserService;
import com.ot.conferences.model.User;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    public List<UserDto> getListUsers() {
        return userService.listUsers()
                .stream()
                .map(u -> dozerBeanMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{login}")
    public UserDto getConference(@PathVariable String login) {
        return dozerBeanMapper.map(userService.getUser(login), UserDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        User user = dozerBeanMapper.map(userDto, User.class);
        return dozerBeanMapper.map(userService.createUser(user), UserDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody @Valid UserDto userDto) {
       User user = dozerBeanMapper.map(userDto, User.class);
        return dozerBeanMapper.map(userService.updateUser(login, user), UserDto.class);
    }

    @DeleteMapping(value = "/user/{login}")
    public ResponseEntity<Void> deleteConference(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }

}
