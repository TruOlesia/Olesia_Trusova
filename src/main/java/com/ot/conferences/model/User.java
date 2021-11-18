package com.ot.conferences.model;

import com.ot.conferences.controller.dto.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String login;
    private transient String password;
    private String fullName;
    private UserRole role;
}
