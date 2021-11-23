package com.ot.conferences.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ParticipantDto {

    @NotNull(message = "id is mandatory")
    private UserDto user;
    @NotNull(message = "id is mandatory")
    private ConferenceDto conf;
    private boolean isPresent;
}
