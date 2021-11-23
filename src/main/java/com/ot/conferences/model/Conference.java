package com.ot.conferences.model;

import com.ot.conferences.controller.dto.ConferenceStatus;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Conferences")
@Data
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "moderator_id", referencedColumnName = "id")
    private User moderator;

    @Column(name = "status")
    private ConferenceStatus status;

}
