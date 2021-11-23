package com.ot.conferences.model;

import com.ot.conferences.controller.dto.TopicStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Topics")
@Data
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "conf_id", referencedColumnName = "id")
    private Conference conference;

    @ManyToOne
    @JoinColumn(name = "speaker_id", referencedColumnName = "id")
    private User speaker;

    @Column(name = "date_time")
    private Date date;

    @Column(name = "status")
    private TopicStatus status;

    @ManyToOne
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    private User createdByUser;
}
