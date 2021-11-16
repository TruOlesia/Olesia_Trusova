package com.ot.conferences.service.model;

import com.ot.conferences.controller.dto.TopicStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Topic {
    private int id;
    private String name;
    private int conferenceId;
    private int speakerId;
    private Date date;
    private TopicStatus status;
    private int createdByUserId;
}
