package com.senai.laziot.device;

import com.senai.laziot.device.DTO.DeviceFiltersDTO;
import com.senai.laziot.device.DTO.DeviceLinkFilterDTO;
import com.senai.laziot.device.DTO.DeviceSimplifiedDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DeviceService {

    boolean saveNewDevice(DeviceEntity newDeviceEntity);
    boolean updateDeviceState(String userToken, int idDevice, boolean newDeviceState);
    boolean linkDevices(String userToken, int idEmitter, int idReceptor);
    boolean unlinkDevices(String userToken, int idEmitter, int idReceptor);


    Page<DeviceEntity> findAllDevicesWithFilter(String userToken, DeviceFiltersDTO deviceFilterDTO, Pageable pageable);
    Page<DeviceEntity> findAllMainDevices(String userToken,Pageable pageable);
    List<DeviceSimplifiedDTO> findAllSimplifiedDevices(String userToken);
    List<DeviceEntity> findAllLinkedDevices(String userToken, DeviceLinkFilterDTO deviceLinkFilterDTO);
    List<DeviceEntity> findAllAvailableDevicesToLink(String userToken, DeviceLinkFilterDTO deviceLinkFilterDTO);
}
