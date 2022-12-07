package com.SimpleEventMaster.awesomeAPP.modelEntity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long capacity;
    private String location;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;


    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "creator")
    private User creator;


}
