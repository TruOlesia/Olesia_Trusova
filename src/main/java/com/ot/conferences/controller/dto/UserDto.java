package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private int id;
    private String login;
    private String fullName;
    private UserRole role;

}
