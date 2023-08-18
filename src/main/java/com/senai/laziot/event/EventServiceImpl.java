package com.senai.laziot.event;

import com.senai.laziot.event.DTO.EventFilterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Long saveNewEvent(Long idActionNewEven) {
        try {
            EventEntity eventEntity = new EventEntity(idActionNewEven);
            return eventRepository.save(eventEntity).getId();
        }catch(Exception e) {
            return 0L;
        }
    }

    @Override
    public List<EventEntity> findAllEventsFiltered(String userToken, EventFilterDTO eventFilterDTO, Pageable pageable) {
        return eventRepository.findAllEventsFiltered(userToken,
                Optional.ofNullable(eventFilterDTO.getDate()).orElse("2000-01-01"),
                Optional.ofNullable(eventFilterDTO.getDateEnd()).orElse("2050-12-31"),
                Optional.ofNullable(eventFilterDTO.getTime()).orElse("00:01:01"),
                Optional.ofNullable(eventFilterDTO.getTimeEnd()).orElse("23:59:59"),
                Optional.ofNullable(eventFilterDTO.getManual()).orElse(List.of(0)),
                Optional.ofNullable(eventFilterDTO.getExecuted()).orElse(List.of(0)));
    }


}
