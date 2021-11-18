package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class TopicDto {

    private int id;
    @NotBlank(message = "Login is mandatory")
    private String name;
    private int conferenceId;
    private int speakerId;
    @NotBlank(message = "Login is mandatory")
    private Date date;
    private TopicStatus status;
    private int createdByUserId;

}
