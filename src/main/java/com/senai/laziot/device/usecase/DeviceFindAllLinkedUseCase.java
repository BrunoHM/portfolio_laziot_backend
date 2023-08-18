package com.senai.laziot.device.usecase;

import com.senai.laziot.device.DTO.DeviceLinkFilterDTO;
import com.senai.laziot.device.DeviceEntity;
import com.senai.laziot.device.DeviceService;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceFindAllLinkedUseCase {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserValidation userValidation;

    public List<DeviceEntity> execute(String userToken, DeviceLinkFilterDTO deviceLinkFilterDTO) throws UserException {
        userValidation.validate(userToken);
        return deviceService.findAllLinkedDevices(userToken, deviceLinkFilterDTO);
    }
}
