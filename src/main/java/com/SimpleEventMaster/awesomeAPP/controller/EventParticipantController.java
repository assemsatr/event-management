package com.SimpleEventMaster.awesomeAPP.controller;


import com.SimpleEventMaster.awesomeAPP.services.EventParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventParticipant")
public class EventParticipantController {

    private EventParticipantService eventParticipantService;


    @Autowired
    public EventParticipantController(EventParticipantService eventParticipantService) {
        this.eventParticipantService = eventParticipantService;
    }

    @PutMapping("/event/{eventId}")
    public void participateEvent(@PathVariable("eventId") long eventId){
        eventParticipantService.participateEvent(eventId);
    }
}
