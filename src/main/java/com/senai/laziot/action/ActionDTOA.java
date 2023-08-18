package com.senai.laziot.action;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActionDTOA {

    private Long id;
    private String description;
    private short triggerIOPin;
    private boolean doubleAction;
    private String delay;
    private Long fkDeviceId;

}
