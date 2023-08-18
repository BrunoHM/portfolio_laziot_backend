package com.senai.laziot.action;

import com.senai.laziot.action.DTO.ActionDTO;
import com.senai.laziot.action.DTO.ActionInsertDTO;
import com.senai.laziot.device.DeviceEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "action")
public class ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "triggerIOPin")
    private short triggerIOPin;

    @Column(name = "doubleAction")
    private boolean doubleAction;

    @Column(name = "delay")
    private String delay;
    
    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "fkDeviceId", referencedColumnName = "id")
    private DeviceEntity fkDeviceId;

    public ActionEntity(ActionDTO actionDTO){
        this.description = actionDTO.getDescription();
        this.doubleAction = actionDTO.isDoubleAction();
        this.delay = actionDTO.getDelay();
        this.active = actionDTO.isActive();
    }

    public ActionEntity(ActionInsertDTO actionInsertDTO){
        this.description = actionInsertDTO.getDescription();
        this.triggerIOPin = actionInsertDTO.getTriggerIOPin();
        this.delay = actionInsertDTO.getDelay();
        this.doubleAction = actionInsertDTO.isDoubleAction();
        this.active = actionInsertDTO.isActive();
        this.fkDeviceId = actionInsertDTO.getDeviceEntityFK();
    }


}
