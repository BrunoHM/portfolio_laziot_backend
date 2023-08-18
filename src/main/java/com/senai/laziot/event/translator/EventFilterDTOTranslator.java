package com.senai.laziot.event.translator;

import com.senai.laziot.event.DTO.EventFilterDTO;
import com.senai.laziot.event.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EventFilterDTOTranslator {

    public List<EventFilterDTO> translateEventEntityToEventFilterDTO(List<EventEntity> eventsPaged){

        if(!Optional.ofNullable(eventsPaged).isEmpty()){
            List<EventFilterDTO> eventFilterDTOPaged = eventsPaged.stream().map(this::translateEntityToDTO).collect(Collectors.toList());;
            return eventFilterDTOPaged;
        }
        return null;
    }

    public EventFilterDTO translateEntityToDTO(EventEntity eventEntity){
        return new EventFilterDTO(eventEntity);
    }

}
