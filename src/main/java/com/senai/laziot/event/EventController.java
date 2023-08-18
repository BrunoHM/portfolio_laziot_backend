package com.senai.laziot.event;

import com.senai.laziot.event.DTO.EventFilterDTO;
import com.senai.laziot.event.useCase.EventFindAllUseCase;
import com.senai.laziot.exception.PageableException;
import com.senai.laziot.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private EventFindAllUseCase eventFindAllUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllEvents(@RequestHeader(name = "token") String userToken,
                                           EventFilterDTO eventFilterDTO,
                                           Pageable pageable) throws UserException {
    return ResponseEntity.ok(eventFindAllUseCase.execute(userToken, eventFilterDTO, pageable));
    }
}
