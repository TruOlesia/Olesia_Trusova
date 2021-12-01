package com.ot.conferences.model;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Embeddable
public class ParticipantKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "conf_id")
    Long confId;
}
