package com.senai.laziot.action.useCase;

import com.senai.laziot.action.ActionEntity;
import com.senai.laziot.action.ActionService;
import com.senai.laziot.action.DTO.ActionDTO;
import com.senai.laziot.action.DTO.ActionFiltersDTO;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionFindAllUseCase {

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private ActionService actionService;

    public List<ActionDTO> execute(String userToken, ActionFiltersDTO actionFiltersDTO, Pageable pageable) throws UserException {
        userValidation.validate(userToken);
        return actionService.findAllActionUser(userToken, actionFiltersDTO, pageable);
    }

}
