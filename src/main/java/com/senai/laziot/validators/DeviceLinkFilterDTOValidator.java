package com.senai.laziot.validators;

import com.senai.laziot.device.DTO.DeviceLinkFilterDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeviceLinkFilterDTOValidator extends RuntimeException {

    public void validate(DeviceLinkFilterDTO deviceLinkFilterDTO){
        Optional.ofNullable(deviceLinkFilterDTO.getId()).orElseThrow(() -> new RuntimeException("Null or incorrect value to 'id' field!"));
        Optional.ofNullable(deviceLinkFilterDTO.getType()).orElseThrow(() -> new RuntimeException("Null or incorrect value to 'type' field!"));
    }
}
