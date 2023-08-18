package com.senai.laziot.action.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActionFiltersDTO {

    private String description;
    private List<Integer> device;
    private List<Integer> doubleAction;
    private List<Integer> active;
}
