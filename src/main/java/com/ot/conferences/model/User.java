package com.ot.conferences.model;

import com.ot.conferences.controller.dto.UserRole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Conferences")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private transient String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role")
    private UserRole role;

}
