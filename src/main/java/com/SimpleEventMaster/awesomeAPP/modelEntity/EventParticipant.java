package com.SimpleEventMaster.awesomeAPP.modelEntity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "event_participant")
public class EventParticipant {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

}
