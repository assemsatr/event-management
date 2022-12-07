package com.SimpleEventMaster.awesomeAPP.mapper;

import com.SimpleEventMaster.awesomeAPP.dto.EventDTO;
import com.SimpleEventMaster.awesomeAPP.modelEntity.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO mapToDTO(Event event);

    List<EventDTO> mapToDTO(List<Event>events);

    Event mapToEvent(EventDTO eventDTO);

    List<Event>mapToEvent(List<EventDTO>eventDTOS);


}
