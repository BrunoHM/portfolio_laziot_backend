package com.senai.laziot.parameters;

import com.senai.laziot.parameters.DTO.UserParameterDTO;
import com.senai.laziot.parameters.DTO.UserParameterSimplifiedDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParametersService {

    List<UserParameterDTO> getAllParamsUser(String tokenUser);
    List<ParametersEntity> getSsidAccessData(String tokenUser);
    List<ParametersEntity> getSensorParameters(String tokenUser);
    List<ParametersEntity> getDefaultParameters();
    boolean updateAllParamsUser(String tokenUser, List<UserParameterDTO> listUserParameterDTO);
    void saveDefaultParameters();



}
