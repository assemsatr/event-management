package com.SimpleEventMaster.awesomeAPP.controller;


import com.SimpleEventMaster.awesomeAPP.dto.EventDTO;
import com.SimpleEventMaster.awesomeAPP.modelEntity.EventStatus;
import com.SimpleEventMaster.awesomeAPP.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/events")
public class EventController {

    private  EventService eventService;


    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }



    @RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public List<EventDTO> findAll() {
        List<EventDTO> eventDTOS = eventService.findAll();
        return eventDTOS;
    }


    //for pagination
    @RequestMapping(value = "/findAllPaginated", method = RequestMethod.GET,
            produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public List<EventDTO> findAllPaginated(@RequestParam(name = "size")int size,
                                           @RequestParam(name = "page")int page,
                                           @RequestParam(name = "direction")Sort.Direction direction,
                                           @RequestParam(name = "fieldName")String fieldName) {

        return eventService.findAllPaginated(PageRequest.of(page,size,direction,fieldName));
    }


//    //for filter
    @RequestMapping(value = "/findAllFilter", method = RequestMethod.GET,
                produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
    public List<EventDTO> findAllFilter(@RequestParam(name = "from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate,
                                        @RequestParam(name = "status") EventStatus status){

        return eventService.findAllFilter(localDate,status);
    }



    @GetMapping("/findById/{eventId}")
    public EventDTO findEventById(@PathVariable("eventId") long eventId) {

        EventDTO eventDTO = eventService.findById(eventId);
        return eventDTO;
    }

    @RequestMapping(
            value = "/addEventOrUpdate",
            method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE)
    public EventDTO addEventOrUpdate(@RequestBody EventDTO event) {
        EventDTO eventDTO = eventService.addEventOrUpdate(event);
        return eventDTO;
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable("eventId") long eventId){
        eventService.deleteEvent(eventId);

    }


}
