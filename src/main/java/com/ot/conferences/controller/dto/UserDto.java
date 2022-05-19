package com.ot.conferences.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotNull(message = "Login is mandatory")
    @Email
    private String login;
    @NotBlank(message = "Name is mandatory")
    private String fullName;
    private UserRole role;

}
