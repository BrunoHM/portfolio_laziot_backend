package com.senai.laziot.parameters.DTO;

import com.senai.laziot.parameters.ParametersEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserParameterDTO {

    private Long id;
    private String description;
    private String value;
    private boolean active;

    public UserParameterDTO(ParametersEntity parametersEntity){
        this.id = parametersEntity.getId();
        this.description = parametersEntity.getDescription();
        this.value = parametersEntity.getValue();
        this.active = parametersEntity.isActive();

    }
}
