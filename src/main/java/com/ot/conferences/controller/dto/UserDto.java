package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    private int id;
    @NotBlank(message = "Login is mandatory")
    @Email
    private String login;
    @NotBlank(message = "Name is mandatory")
    private String fullName;
    private UserRole role;
}
