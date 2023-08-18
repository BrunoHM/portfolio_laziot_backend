package com.senai.laziot.codeDevice.useCases;

import com.senai.laziot.codeDevice.CodeDeviceEntity;
import com.senai.laziot.codeDevice.CodeDeviceService;
import com.senai.laziot.codeDevice.utils.CodeDeviceUtils;
import com.senai.laziot.parameters.ParametersEntity;
import com.senai.laziot.parameters.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class GetEmissorCodesUseCase {

    @Autowired
    private CodeDeviceService codeDeviceService;

    @Autowired
    private ParametersService parametersService;

    @Autowired
    private CodeDeviceUtils utils;

    public CodeDeviceEntity execute(String userHash){
        List<ParametersEntity> listParameters = parametersService.getSsidAccessData(userHash);
        List<ParametersEntity> listDefaultParameters = parametersService.getDefaultParameters();
        List<ParametersEntity> listSensorParameters = parametersService.getSensorParameters(userHash);
        CodeDeviceEntity codeDeviceEntity = codeDeviceService.getEmissorCode();

        codeDeviceEntity = utils.replaceSSIDdata(listParameters, codeDeviceEntity);
        codeDeviceEntity = utils.replaceMqttData(listDefaultParameters, codeDeviceEntity);
        codeDeviceEntity = utils.replaceSensorParams(listSensorParameters, codeDeviceEntity);
        codeDeviceEntity = utils.replaceUserHash(codeDeviceEntity, userHash);

        return codeDeviceEntity;
    }
}
