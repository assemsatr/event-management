package com.SimpleEventMaster.awesomeAPP.services;


import com.SimpleEventMaster.awesomeAPP.modelEntity.Event;
import com.SimpleEventMaster.awesomeAPP.modelEntity.EventParticipant;
import com.SimpleEventMaster.awesomeAPP.modelEntity.User;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.EventParticipantRepository;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.EventRepository;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EventParticipantService {

    private final EventParticipantRepository eventParticipantRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;


    public EventParticipantService(EventParticipantRepository eventParticipantRepository, UserRepository userRepository, EventRepository eventRepository) {
        this.eventParticipantRepository = eventParticipantRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public void participateEvent(long eventId){

        final var loggedInUser= SecurityContextHolder.getContext().getAuthentication().getName();

        User theUser=userRepository.findByEmail(loggedInUser).get();

        Event theEvent=eventRepository.findById(eventId).get();

        EventParticipant eventParticipant=new EventParticipant();

        eventParticipant.setEvent(theEvent);
        eventParticipant.setUser(theUser);

        eventParticipantRepository.save(eventParticipant);


    }
}
