package com.senai.laziot.action.useCase;

import com.senai.laziot.action.ActionService;
import com.senai.laziot.action.DTO.ActionStateUpdateDTO;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateActionStateUseCase {

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private ActionService actionService;


    public boolean execute(String userToken, ActionStateUpdateDTO actionStateUpdateDTO) throws UserException {
        userValidation.validate(userToken);
        return actionService.updateActionState(userToken, actionStateUpdateDTO);
    }
}
