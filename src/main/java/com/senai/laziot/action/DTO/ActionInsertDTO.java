package com.senai.laziot.action.DTO;

import com.senai.laziot.device.DeviceEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActionInsertDTO {

    private String description;
    private String delay;
    private boolean doubleAction;
    private boolean active;
    private short triggerIOPin;
    private Long deviceId;
    private DeviceEntity deviceEntityFK;

}
