package com.senai.laziot.device.DTO;

import com.senai.laziot.device.DeviceEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class DeviceSimplifiedDTO {

    private Long id;
    private String description;

    public DeviceSimplifiedDTO(DeviceEntity deviceEntity) {
        this.id = deviceEntity.getId();
        this.description = deviceEntity.getDescription();
    }
}
