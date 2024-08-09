package com.senai.laziot.device;

import com.senai.laziot.device.DTO.DeviceFiltersDTO;
import com.senai.laziot.device.DTO.DeviceLinkFilterDTO;
import com.senai.laziot.device.DTO.DeviceSimplifiedDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;


    @Override
    @Transactional
    public boolean saveNewDevice(DeviceEntity newDeviceEntity) {
        try {
            deviceRepository.save(newDeviceEntity);
            return true;
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateDeviceState(String userToken, int idDevice, boolean newDeviceState) {
        int successUpdate = deviceRepository.updateDeviceState(userToken, idDevice, newDeviceState);
        return successUpdate == 1;
    }

    @Override
    public boolean linkDevices(String userToken, int idEmitter, int idReceptor) {
        int successLink = deviceRepository.setLinkDevices(idEmitter, idReceptor);
        return successLink == 1;
    }

    @Override
    public boolean unlinkDevices(String userToken, int idEmitter, int idReceptor) {
        int successUnLink = deviceRepository.setUnlinkDevices(idEmitter, idReceptor);
        return successUnLink == 1;
    }

    @Override
    public Page<DeviceEntity> findAllDevicesWithFilter(String userToken, DeviceFiltersDTO deviceFilterDTO, Pageable pageable) {
        return deviceRepository.findAllByDeviceFiltersDTO(userToken,
                Optional.ofNullable(deviceFilterDTO.getDescription()).orElse(""),
                Optional.ofNullable(deviceFilterDTO.getPlace()).orElse(""),
                Optional.ofNullable(deviceFilterDTO.getType()).orElse(""),
                Optional.ofNullable(deviceFilterDTO.getActive()).orElse(List.of(1)),
                pageable);
    }

    @Override
    public List<DeviceSimplifiedDTO> findAllSimplifiedDevices(String userToken) {
        List<DeviceEntity> listEntity = deviceRepository.findAllSimplifiedDevices(userToken);
        List<DeviceSimplifiedDTO> listSimplified =  listEntity.stream().map(obj -> new DeviceSimplifiedDTO(obj)).collect(Collectors.toList());
        return listSimplified;
    }

    @Override
    public Page<DeviceEntity> findAllMainDevices(String userToken, Pageable pageable) {
        return deviceRepository.findAllMainDevices(userToken, pageable);
    }

    @Override
    public List<DeviceEntity> findAllLinkedDevices(String userToken, DeviceLinkFilterDTO deviceLinkFilterDTO) {
        return deviceRepository.findAllLinkedDevices(userToken, deviceLinkFilterDTO.getId());
    }

    @Override
    public List<DeviceEntity> findAllAvailableDevicesToLink(String userToken, DeviceLinkFilterDTO deviceLinkFilterDTO) {
        return deviceRepository.findAllAvailableDevicesToLink(userToken, deviceLinkFilterDTO.getId(), deviceLinkFilterDTO.getType());
    }

}
