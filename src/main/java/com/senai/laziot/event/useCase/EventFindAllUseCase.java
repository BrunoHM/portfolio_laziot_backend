package com.senai.laziot.event.useCase;

import com.senai.laziot.event.DTO.EventFilterDTO;
import com.senai.laziot.event.EventEntity;
import com.senai.laziot.event.EventService;
import com.senai.laziot.event.translator.EventFilterDTOTranslator;
import com.senai.laziot.exception.PageableException;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import com.senai.laziot.validators.PageableValidator;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventFindAllUseCase {

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventFilterDTOTranslator eventFilterDTOTranslator;

    public List<EventFilterDTO> execute(String userToken, EventFilterDTO eventFilterDTO, Pageable pageable) throws UserException {
        userValidation.validate(userToken);
        return eventFilterDTOTranslator.translateEventEntityToEventFilterDTO(eventService.findAllEventsFiltered(userToken, eventFilterDTO, pageable));
    }
}
