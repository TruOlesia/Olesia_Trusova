package com.ot.conferences.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ParticipantKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "conf_id")
    Long confId;
}
