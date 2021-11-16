package com.ot.conferences.service.model;

import com.ot.conferences.controller.dto.ConferenceStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Conference {

    private int id;
    private String name;
    private String location;
    private Date startDate;
    private Date endDate;
    private int moderatorId;
    private String description;
    private ConferenceStatus status;
}
