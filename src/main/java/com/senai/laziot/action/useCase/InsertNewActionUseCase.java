package com.senai.laziot.action.useCase;

import com.senai.laziot.action.ActionService;
import com.senai.laziot.action.DTO.ActionDTO;
import com.senai.laziot.action.DTO.ActionInsertDTO;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertNewActionUseCase {

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private ActionService actionService;

    public boolean execute(String userHash, ActionInsertDTO actionInsertDTO) throws UserException {
        userValidation.validate(userHash);
        return actionService.saveNewAction(actionInsertDTO);
    }
}
