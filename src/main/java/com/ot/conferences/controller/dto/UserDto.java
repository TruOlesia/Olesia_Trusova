package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class UserDto {

    private int Long;
    @NotNull(message = "Login is mandatory")
    @Email
    private String login;
    @NotBlank(message = "Name is mandatory")
    private String fullName;
    private UserRole role;

}
