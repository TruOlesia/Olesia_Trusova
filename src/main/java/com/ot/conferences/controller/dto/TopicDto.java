package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class TopicDto {

    private Long id;
    @NotNull(message = "Name is mandatory")
    private String name;
    private ConferenceDto conference;
    private UserDto speaker;
    @NotNull(message = "date is mandatory")
    private Date date;
    private TopicStatus status;
    private UserDto createdByUser;

}
