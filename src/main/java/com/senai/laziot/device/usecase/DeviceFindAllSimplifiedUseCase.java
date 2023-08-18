package com.senai.laziot.device.usecase;

import com.senai.laziot.device.DTO.DeviceFiltersDTO;
import com.senai.laziot.device.DTO.DeviceSimplifiedDTO;
import com.senai.laziot.device.DeviceEntity;
import com.senai.laziot.device.DeviceService;
import com.senai.laziot.exception.PageableException;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceFindAllSimplifiedUseCase {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserValidation userValidation;

    public List<DeviceSimplifiedDTO> execute(String userToken) throws UserException {
        userValidation.validate(userToken);
        return deviceService.findAllSimplifiedDevices(userToken);
    }
}
