package com.senai.laziot.action.DTO;

import com.senai.laziot.action.ActionEntity;
import com.senai.laziot.device.DeviceEntity;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class ActionDTO {

    public ActionDTO(ActionEntity actionEntity){
        this.id = actionEntity.getId();
        this.description = actionEntity.getDescription();
        this.delay = actionEntity.getDelay();
        this.deviceDescription = actionEntity.getFkDeviceId().getDescription();
        this.doubleAction = actionEntity.isDoubleAction();
        this.active = actionEntity.isActive();
    }

    private Long id;
    private String description;
    private boolean doubleAction;
    private String delay;
    private boolean active;
    private String deviceDescription;
}
