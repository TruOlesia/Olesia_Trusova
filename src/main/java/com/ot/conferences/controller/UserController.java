package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.ParticipantDto;
import com.ot.conferences.controller.dto.UserDto;
import com.ot.conferences.exception.NotFoundException;
import com.ot.conferences.model.User;
import com.ot.conferences.service.ParticipantService;
import com.ot.conferences.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public List<UserDto> getListUsers(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return userService.listUsers(paging)
                .stream()
                .map(u -> dozerBeanMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NotFoundException("Invalid user id : " + id);
        }
        return dozerBeanMapper.map(userService.getUser(id), UserDto.class);
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
    @PutMapping(value = "/user/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        User user = dozerBeanMapper.map(userDto, User.class);
        if (user == null || user.getId() == null) {
            throw new NotFoundException("Invalid user id : " + id);
        }
        return dozerBeanMapper.map(userService.save(user), UserDto.class);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NotFoundException("Invalid user login : " + id);
        }
        userService.deleteUser(id);
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
