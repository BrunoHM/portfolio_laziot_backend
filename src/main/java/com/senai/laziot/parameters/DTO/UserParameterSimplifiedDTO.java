package com.senai.laziot.parameters.DTO;

import com.senai.laziot.parameters.ParametersEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserParameterSimplifiedDTO {

    private String description;
    private String value;

    public UserParameterSimplifiedDTO(ParametersEntity parametersEntity){
        this.description = parametersEntity.getDescription();
        this.value = parametersEntity.getValue();
    }
}
