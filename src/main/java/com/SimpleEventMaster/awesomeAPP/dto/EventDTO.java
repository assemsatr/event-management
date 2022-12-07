package com.SimpleEventMaster.awesomeAPP.dto;


import com.SimpleEventMaster.awesomeAPP.modelEntity.EventStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id",scope = Long.class)
public class EventDTO {

    private long id;
    private String name;
    private long capacity;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private EventStatus eventStatus;
}
