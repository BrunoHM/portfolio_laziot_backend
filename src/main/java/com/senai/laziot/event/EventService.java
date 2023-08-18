package com.senai.laziot.event;

import com.senai.laziot.event.DTO.EventFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EventService {

    Long saveNewEvent(Long idActionNewEvent);
    List<EventEntity> findAllEventsFiltered(String userToken, EventFilterDTO eventFilterDTO, Pageable pageable);
}
