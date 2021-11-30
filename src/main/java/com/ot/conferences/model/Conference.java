package com.ot.conferences.model;

import com.ot.conferences.controller.dto.ConferenceStatus;
import lombok.Builder;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "conference")
@Data
@Builder
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "conf_name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id", referencedColumnName = "id")
    private User moderator;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ConferenceStatus status;

}
