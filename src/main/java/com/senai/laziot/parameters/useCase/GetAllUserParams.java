package com.senai.laziot.parameters.useCase;

import com.senai.laziot.exception.UserException;
import com.senai.laziot.parameters.DTO.UserParameterDTO;
import com.senai.laziot.parameters.ParametersService;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUserParams {

    @Autowired
    private ParametersService parametersService;

    @Autowired
    private UserValidation userValidation;

    public List<UserParameterDTO> execute(String userToken) throws UserException {
        userValidation.validate(userToken);

        return parametersService.getAllParamsUser(userToken);
    }
}
