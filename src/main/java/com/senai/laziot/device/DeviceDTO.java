package com.senai.laziot.device;

import lombok.*;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class DeviceDTO {


    private Long id;
    private String description;
    private String place;
    private String uniqueDeviceCode;
    private Short qtdPinsIO;
    private String type;
    private boolean active;

}
