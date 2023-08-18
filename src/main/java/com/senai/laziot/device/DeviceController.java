package com.senai.laziot.device;

import com.senai.laziot.device.DTO.DeviceFiltersDTO;
import com.senai.laziot.device.DTO.DeviceLinkFilterDTO;
import com.senai.laziot.device.DTO.DeviceLinksDTO;
import com.senai.laziot.device.DTO.DeviceUpdateDTO;
import com.senai.laziot.device.usecase.*;
import com.senai.laziot.exception.PageableException;
import com.senai.laziot.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/device")
public class DeviceController {

    @Autowired
    private DeviceFindAllUseCase deviceFindAllUseCase;

    @Autowired
    private DeviceFindAllMainUseCase deviceFindAllMainUseCase;

    @Autowired
    private UpdateDeviceStateUseCase updateDeviceStateUseCase;

    @Autowired
    private DevicefindAllAvailableDevicesToLinkUseCase devicefindAllAvailableDevicesToLinkUseCase;

    @Autowired
    private DeviceFindAllLinkedUseCase deviceFindAllLinkedUseCase;

    @Autowired
    private LinkDevicesUseCase linkDevicesUseCase;

    @Autowired
    private UnlinkDevicesUseCase unlinkDevicesUseCase;

    @Autowired
    private DeviceFindAllSimplifiedUseCase deviceFindAllSimplifiedUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllDevices(@RequestHeader(name = "token") String userToken,
                                            DeviceFiltersDTO deviceFiltersDTO, Pageable pageable) throws UserException, PageableException {
        return ResponseEntity.ok(deviceFindAllUseCase.execute(userToken, deviceFiltersDTO, pageable));
    }

    @GetMapping(value = "/simplified", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllSimplifiedDevices(@RequestHeader(name = "token") String userToken) throws UserException {
        return ResponseEntity.ok(deviceFindAllSimplifiedUseCase.execute(userToken));
    }

    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateDeviceActiveState(@RequestHeader(name = "token") String userToken, @RequestBody DeviceUpdateDTO deviceUpdateDTO) throws UserException {
        return ResponseEntity.ok(updateDeviceStateUseCase.execute(userToken, deviceUpdateDTO));
    }

    @GetMapping(value = "/getMain", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllMainDevices(@RequestHeader(name = "token") String userToken, Pageable pageable) throws UserException, PageableException {
        return ResponseEntity.ok(deviceFindAllMainUseCase.execute(userToken, pageable));
    }

    @GetMapping(value = "/getLinked", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllLinkedDevices(@RequestHeader(name = "token") String userToken, DeviceLinkFilterDTO deviceLinkFilterDTO) throws UserException {
        return ResponseEntity.ok(deviceFindAllLinkedUseCase.execute(userToken, deviceLinkFilterDTO));
    }

    @GetMapping(value = "/getAvailableToLink", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllAvailableDevicesToLink(@RequestHeader(name = "token") String userToken,
                                                           DeviceLinkFilterDTO deviceLinkFilterDTO ) throws UserException {
        return ResponseEntity.ok(devicefindAllAvailableDevicesToLinkUseCase.execute(userToken, deviceLinkFilterDTO));
    }

    @PostMapping(value = "/linkTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> linkDevices(@RequestHeader(name = "token") String userToken, @RequestBody DeviceLinksDTO deviceLinksDTO) throws UserException {
        return ResponseEntity.ok(linkDevicesUseCase.execute(userToken, deviceLinksDTO));
    }

    @PostMapping(value = "/unlinkTo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unlinkDevices(@RequestHeader(name = "token") String userToken, @RequestBody DeviceLinksDTO deviceLinksDTO) throws UserException {
        return ResponseEntity.ok(unlinkDevicesUseCase.execute(userToken, deviceLinksDTO));
    }
}
