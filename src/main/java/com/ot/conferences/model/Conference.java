package com.ot.conferences.model;

import com.ot.conferences.controller.dto.ConferenceStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
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
