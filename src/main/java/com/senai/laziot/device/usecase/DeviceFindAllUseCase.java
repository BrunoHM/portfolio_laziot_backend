package com.senai.laziot.device.usecase;

import com.senai.laziot.device.DTO.DeviceFiltersDTO;
import com.senai.laziot.device.DeviceEntity;
import com.senai.laziot.device.DeviceService;
import com.senai.laziot.exception.UserException;
import com.senai.laziot.exception.PageableException;
import com.senai.laziot.utils.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class DeviceFindAllUseCase {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserValidation userValidation;

    public Page<DeviceEntity> execute(String userToken, DeviceFiltersDTO deviceFilterDTO, Pageable pageable) throws UserException, PageableException {
        userValidation.validate(userToken);
        return deviceService.findAllDevicesWithFilter(userToken, deviceFilterDTO, pageable);
    }
}
