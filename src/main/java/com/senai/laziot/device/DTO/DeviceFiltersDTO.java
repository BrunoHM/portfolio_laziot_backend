package com.senai.laziot.device.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeviceFiltersDTO {

    private String description;
    private String place;
    private String type;
    private List<Integer> active;

}
