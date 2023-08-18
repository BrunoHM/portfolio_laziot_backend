package com.senai.laziot.device.usecase;

import com.senai.laziot.device.DTO.DeviceLinksDTO;
import com.senai.laziot.device.DeviceService;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnlinkDevicesUseCase {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserValidation userValidation;

    public boolean execute(String userToken, DeviceLinksDTO deviceLinksDTO) throws UserException {
        userValidation.validate(userToken);
        return deviceService.unlinkDevices(userToken, deviceLinksDTO.getIdEmitter(), deviceLinksDTO.getIdReceptor());
    }

}
