package com.senai.laziot.action;

import com.senai.laziot.action.DTO.ActionDTO;
import com.senai.laziot.action.DTO.ActionFiltersDTO;
import com.senai.laziot.action.DTO.ActionInsertDTO;
import com.senai.laziot.action.DTO.ActionStateUpdateDTO;
import com.senai.laziot.device.DeviceEntity;
import com.senai.laziot.device.DeviceRepository;
import com.senai.laziot.device.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Transactional
    public boolean saveNewAction(ActionInsertDTO actionInsertDTO){
        try {
            DeviceEntity deviceEntity = deviceRepository.getDeviceEntityById(actionInsertDTO.getDeviceId());
            actionInsertDTO.setDeviceEntityFK(deviceEntity);
            ActionEntity actionEntity = new ActionEntity(actionInsertDTO);
            actionRepository.save(actionEntity);
            return true;
        }catch(Exception e){
            e.fillInStackTrace();
            throw e;
        }
    }

    @Override
    public List<ActionDTO> findAllActionUser(String userToken, ActionFiltersDTO actionFiltersDTO, Pageable pageable) {
        List<ActionEntity> list = actionRepository.findAllActionUser(userToken,
                Optional.ofNullable(actionFiltersDTO.getDescription()).orElse(""),
                Optional.ofNullable(actionFiltersDTO.getDoubleAction()).orElse(List.of(1)),
                Optional.ofNullable(actionFiltersDTO.getActive()).orElse(List.of(1)),
                pageable);
        List<ActionDTO> lista =  list.stream().map(obj -> new ActionDTO(obj)).collect(Collectors.toList());
        return lista;
    }

    @Override
    public boolean updateActionState(String userToken, ActionStateUpdateDTO actionStateUpdateDTO) {
        int sucessLink = actionRepository.updateActionState(userToken, actionStateUpdateDTO.getId(), actionStateUpdateDTO.isState());
        if(sucessLink == 1) return true;
        return false;
    }


}
