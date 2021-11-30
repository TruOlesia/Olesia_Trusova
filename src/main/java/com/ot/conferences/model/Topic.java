package com.ot.conferences.model;

import com.ot.conferences.controller.dto.TopicStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topic")
@Data
@Builder
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conf_id", referencedColumnName = "id")
    private Conference conference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "speaker_id", referencedColumnName = "id")
    private User speaker;

    @Column(name = "date_time")
    private Date date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TopicStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id", referencedColumnName = "id")
    private User createdByUser;
}
