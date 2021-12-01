package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ParticipantDto {

    @NotNull(message = "user is mandatory")
    private UserDto user;
    @NotNull(message = "conference is mandatory")
    private ConferenceDto conf;
    private boolean isPresent;
}
