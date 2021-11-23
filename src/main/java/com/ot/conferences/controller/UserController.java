package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.ParticipantDto;
import com.ot.conferences.controller.dto.UserDto;
import com.ot.conferences.exception.NotFoundException;
import com.ot.conferences.model.User;
import com.ot.conferences.service.ParticipantService;
import com.ot.conferences.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    public List<UserDto> getListUsers(@RequestParam Pageable paging) {
        return userService.listUsers(paging)
                .stream()
                .map(u -> dozerBeanMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{login}")
    public UserDto getUser(@PathVariable String login) {
        User user = userService.getUser(login);
        if (user == null) {
            throw new NotFoundException("Invalid user login : " + login);
        }
        return dozerBeanMapper.map(userService.getUser(login), UserDto.class);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        User user = dozerBeanMapper.map(userDto, User.class);
        if (user == null) {
            throw new NotFoundException("Invalid user");
        }
        return dozerBeanMapper.map(userService.save(user), UserDto.class);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody @Valid UserDto userDto) {
        User user = dozerBeanMapper.map(userDto, User.class);
        if (user == null || user.getId() == null) {
            throw new NotFoundException("Invalid user login : " + login);
        }
        return dozerBeanMapper.map(userService.save(user), UserDto.class);
    }

    @DeleteMapping(value = "/user/{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        User user = userService.getUser(login);
        if (user == null) {
            throw new NotFoundException("Invalid user login : " + login);
        }
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{id}/participant")
    public List<ParticipantDto> getParticipants(@PathVariable Long id) {

        return participantService.getAllParticipantByUserId(id)
                .stream()
                .map(c -> dozerBeanMapper.map(c, ParticipantDto.class))
                .collect(Collectors.toList());
    }
}
