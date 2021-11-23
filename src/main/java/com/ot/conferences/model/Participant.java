package com.ot.conferences.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Participant")
@Data
public class Participant {

    @EmbeddedId
    ParticipantKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("confId")
    @JoinColumn(name = "conf_id")
    private Conference conference;

    private boolean isPresent;
}
