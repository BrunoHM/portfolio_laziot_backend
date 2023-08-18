package com.senai.laziot.device.usecase;

import com.senai.laziot.device.DTO.DeviceUpdateDTO;
import com.senai.laziot.device.DeviceService;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateDeviceStateUseCase {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserValidation userValidation;

    public boolean execute(String userToken, DeviceUpdateDTO deviceUpdateDTO) throws UserException {
        userValidation.validate(userToken);

        Optional.ofNullable(deviceUpdateDTO.getId() > 0).orElseThrow(() -> new RuntimeException("The field 'idDevice' must be sent!"));
        Optional.ofNullable(deviceUpdateDTO.isState()).orElseThrow(() -> new RuntimeException("The field 'newDeviceState' must be sent!"));

        return deviceService.updateDeviceState(userToken, deviceUpdateDTO.getId(), deviceUpdateDTO.isState());
    }
}
