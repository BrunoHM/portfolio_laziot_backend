package com.senai.laziot.action;

import com.senai.laziot.action.DTO.ActionDTO;
import com.senai.laziot.action.DTO.ActionFiltersDTO;
import com.senai.laziot.action.DTO.ActionInsertDTO;
import com.senai.laziot.action.DTO.ActionStateUpdateDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ActionService {

    boolean saveNewAction(ActionInsertDTO actionInsertDTO);
    List<ActionDTO> findAllActionUser(String userToken, ActionFiltersDTO actionFiltersDTO, Pageable pageable);
    boolean updateActionState(String userToken, ActionStateUpdateDTO actionStateUpdateDTO);
}
