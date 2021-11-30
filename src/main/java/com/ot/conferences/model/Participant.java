package com.ot.conferences.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "participant")
@Data
@Builder
public class Participant {

    @EmbeddedId
    ParticipantKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("confId")
    @JoinColumn(name = "conf_id")
    private Conference conference;

    private boolean isPresent;
}
