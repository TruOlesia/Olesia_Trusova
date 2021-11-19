package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class TopicDto {

    private int id;
    @NotNull(message = "Name is mandatory")
    private String name;
    private int conferenceId;
    private int speakerId;
    @NotNull(message = "date is mandatory")
    private Date date;
    private TopicStatus status;
    private int createdByUserId;

}
