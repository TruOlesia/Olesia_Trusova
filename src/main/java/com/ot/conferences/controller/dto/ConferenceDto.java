package com.ot.conferences.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConferenceDto {

    private int id;
    private String name;
    private String location;
    private Date startDate;
    private Date endDate;
    private ConferenceStatus status;
    private int moderatorId;
    private String description;
}
