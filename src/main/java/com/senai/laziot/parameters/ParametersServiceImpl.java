package com.senai.laziot.parameters;

import com.senai.laziot.parameters.DTO.UserParameterDTO;
import com.senai.laziot.user.UserEntity;
import com.senai.laziot.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ParametersServiceImpl implements ParametersService {

    @Autowired
    private ParametersRepository parametersRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserParameterDTO> getAllParamsUser(String tokenUser) {
        List<ParametersEntity> parametersList    = parametersRepository.getParametersEntitiesByUserHashUniqueCode(tokenUser);
        List<UserParameterDTO> parametersDTOList = parametersList.stream().map(obj -> new UserParameterDTO(obj)).collect(Collectors.toList());
        return parametersDTOList;
    }

    @Override
    public List<ParametersEntity> getSsidAccessData(String tokenUser) {
        List<ParametersEntity> listParametersEntity = parametersRepository.getParametersEntityByDescriptionContainsAndUserHashUniqueCode("SSID_",tokenUser);
        if(!listParametersEntity.isEmpty()) {
            return listParametersEntity;
        }
        return null;
    }

    @Override
    public List<ParametersEntity> getSensorParameters(String tokenUser) {
        List<ParametersEntity> listParametersEntity = parametersRepository.getParametersEntityByDescriptionContainsAndUserHashUniqueCode("SENSOR_DIST_HC-SR04",tokenUser);
        if(!listParametersEntity.isEmpty()) {
            return listParametersEntity;
        }
        return null;
    }

    @Override
    public List<ParametersEntity> getDefaultParameters() {
        List<ParametersEntity> listParametersEntity = parametersRepository.getParametersEntityByUserId(1L);
        if(!listParametersEntity.isEmpty()) {
            return listParametersEntity;
        }
        return null;
    }

    @Override
    public boolean updateAllParamsUser(String tokenUser, List<UserParameterDTO> listUserParameterDTO) {
        AtomicReference<Boolean> opUpdate = new AtomicReference<>(false);
        try {
            if(!listUserParameterDTO.isEmpty()) {
                listUserParameterDTO.stream().forEach((obj) -> {
                    if(!Optional.ofNullable(obj.getId()).isEmpty()) {
                        ParametersEntity parameter = parametersRepository.getParametersEntityByIdAndDescription(obj.getId(), obj.getDescription());
                        parameter.setValue(obj.getValue());
                        parametersRepository.save(parameter);
                        opUpdate.set(true);
                    } else {
                        if(opUpdate.get()) {
                            new Exception("Failed to update user parameters");
                        }
                    }
                });

                if(!opUpdate.get()){
                    UserEntity user = userRepository.getUserEntityByHashUniqueCode(tokenUser);
                    listUserParameterDTO.stream().forEach((obj) -> {
                        ParametersEntity parameter = new ParametersEntity(obj.getDescription(), obj.getValue(), user);
                        parametersRepository.save(parameter);
                    });
                }

                return true;
            } else {
                return false;
            }
        }catch(Exception e) {
            return false;
        }
    }

    @Override
    @PostConstruct
    public void saveDefaultParameters() {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            String ipLocal = datagramSocket.getLocalAddress().getHostAddress();

            if(!ipLocal.isEmpty()){
                insertOrUpdateParam("mqttServer", ipLocal);
            }
            insertOrUpdateParam("mqttPort", "1883");

        }catch(Exception e){
            new Exception(e);
        }
    }

    private boolean insertOrUpdateParam(String paramName, String value){
        ParametersEntity parameter = parametersRepository.getParametersEntityByDescriptionContains(paramName);

        if(parameter != null){
            parameter.setValue(value);
        } else {
            parameter = new ParametersEntity(paramName, value, userRepository.getUserEntityById(1L));
        }

        System.out.println(parametersRepository.save(parameter));
        return false;
    }
}
