package com.senai.laziot.action;

import com.senai.laziot.action.DTO.ActionFiltersDTO;
import com.senai.laziot.action.DTO.ActionInsertDTO;
import com.senai.laziot.action.DTO.ActionStateUpdateDTO;
import com.senai.laziot.action.useCase.ActionFindAllUseCase;
import com.senai.laziot.action.useCase.InsertNewActionUseCase;
import com.senai.laziot.action.useCase.UpdateActionStateUseCase;
import com.senai.laziot.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/action")
public class ActionController {

    @Autowired
    private ActionFindAllUseCase actionFindAllUseCase;

    @Autowired
    private UpdateActionStateUseCase updateActionStateUseCase;

    @Autowired
    private InsertNewActionUseCase insertNewActionUseCase;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewAction(@RequestHeader(name = "token") String userToken, @RequestBody ActionInsertDTO actionInsertDTO) throws UserException {
        return ResponseEntity.ok(insertNewActionUseCase.execute(userToken, actionInsertDTO));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllActionUser(@RequestHeader(name = "token") String userToken, ActionFiltersDTO actionFiltersDTO, Pageable pageable) throws UserException {
        return ResponseEntity.ok(actionFindAllUseCase.execute(userToken, actionFiltersDTO, pageable));
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateActionActiveState(@RequestHeader(name = "token") String userToken, @RequestBody ActionStateUpdateDTO actionStateUpdateDTO) throws UserException {
        return ResponseEntity.ok(updateActionStateUseCase.execute(userToken, actionStateUpdateDTO));
    }

}
