package com.ot.conferences.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
