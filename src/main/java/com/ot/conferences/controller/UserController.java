package com.ot.conferences.controller;

import com.ot.conferences.controller.dto.ConferenceDto;
import com.ot.conferences.controller.dto.UserDto;
import com.ot.conferences.service.ConferenceService;
import com.ot.conferences.service.UserService;
import com.ot.conferences.service.model.Conference;
import com.ot.conferences.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users")
    public List<UserDto> getListUsers() {
        return userService.listUsers()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/user/{login}")
    public UserDto getConference(@PathVariable String login) {
        return mapUserToUserDto(userService.getUser(login));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/user")
    public UserDto createUser(@RequestBody User user) {
        return mapUserToUserDto(userService.createUser(user));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody User user) {
        return mapUserToUserDto(userService.updateUser(login, user));
    }

    @DeleteMapping(value = "/user/{login}")
    public ResponseEntity<Void> deleteConference(@PathVariable String login) {
        userService.deleteUser(login);
        return ResponseEntity.noContent().build();
    }


    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .login(user.getLogin())
                .fullName(user.getFullName())
                .role(user.getRole())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .login(userDto.getLogin())
                .fullName(userDto.getFullName())
                .role(userDto.getRole())
                .build();
    }
}
