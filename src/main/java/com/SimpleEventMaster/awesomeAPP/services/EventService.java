package com.SimpleEventMaster.awesomeAPP.services;


import com.SimpleEventMaster.awesomeAPP.dto.EventDTO;
import com.SimpleEventMaster.awesomeAPP.mapper.EventMapper;
import com.SimpleEventMaster.awesomeAPP.modelEntity.Event;
import com.SimpleEventMaster.awesomeAPP.modelEntity.EventStatus;
import com.SimpleEventMaster.awesomeAPP.modelEntity.User;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.EventRepository;
import com.SimpleEventMaster.awesomeAPP.repositoryDAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    private final UserRepository userRepository;


    public EventService(EventRepository eventRepository, EventMapper eventMapper, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userRepository = userRepository;
    }

    public List<EventDTO> findAll(){

        List<Event>events=eventRepository.findAll();

        return eventMapper.mapToDTO(events);

    }

    public EventDTO findById(long eventId){
        Event event =eventRepository.findById(eventId).orElse(null);
        return eventMapper.mapToDTO(event);
    }

    public EventDTO addEventOrUpdate(EventDTO eventDTO){
        final var loggedInUser= SecurityContextHolder.getContext().getAuthentication().getName();
        User eventCreator=userRepository.findByEmail(loggedInUser).get();

        Event event=eventMapper.mapToEvent(eventDTO);

        event.setCreator(eventCreator);

         Event theNewEvent=eventRepository.save(event);


         return eventMapper.mapToDTO(theNewEvent);
    }

    public void deleteEvent(long id){
        try {
            eventRepository.deleteById(id);
        }
        catch (Exception e){
            e.getMessage();
            throw new RuntimeException("Event id "+id+" doesnt exist");
        }
    }


    public List<EventDTO> findAllPaginated(PageRequest pageRequest) {
        List<Event>content=eventRepository.findAll(pageRequest).getContent();
        return eventMapper.mapToDTO(content);
    }

    public List<EventDTO>findAllFilter(LocalDate localDate, EventStatus status){
        List<Event>foundEvent=eventRepository.findByStartDateAfterAndStatus(localDate.minusDays(1),status);
        return eventMapper.mapToDTO(foundEvent);
    }




}
