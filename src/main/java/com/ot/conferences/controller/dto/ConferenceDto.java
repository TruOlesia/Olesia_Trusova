package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ConferenceDto {
    private int id;
    @NotBlank(message = "Login is mandatory")
    private String name;
    @NotBlank(message = "Login is mandatory")
    private String location;
    @NotBlank(message = "Login is mandatory")
    @FutureOrPresent
    private Date startDate;
    private Date endDate;
    private ConferenceStatus status;
    private int moderatorId;
    @NotBlank(message = "Login is mandatory")
    private String description;
}
