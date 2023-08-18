package com.senai.laziot.codeDevice.utils;

import com.senai.laziot.codeDevice.CodeDeviceEntity;
import com.senai.laziot.parameters.ParametersEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CodeDeviceUtils {

    //#distSensor# = distancia(cm) do sensor ultrasonico;
    //@@ = \" = 'hack' do regex das aspas;
    public CodeDeviceEntity replaceSSIDdata(List<ParametersEntity> listParametersEntity, CodeDeviceEntity codeDeviceEntity){

        try {
            String textCode = codeDeviceEntity.getTextCode();
            textCode = textCode.replaceAll("#ssid#", listParametersEntity.get(0).getValue());
            textCode = textCode.replaceAll("#ssidPassword#", listParametersEntity.get(1).getValue());
            codeDeviceEntity.setTextCode(textCode);

            return codeDeviceEntity;
        }catch (Exception e){
            throw e;
        }
    }

    public CodeDeviceEntity replaceMqttData(List<ParametersEntity> listParametersEntity, CodeDeviceEntity codeDeviceEntity) {
        try {
            String textCode = codeDeviceEntity.getTextCode();
            textCode = textCode.replaceAll("#ipMqttServer#", listParametersEntity.get(0).getValue());
            textCode = textCode.replaceAll("#portMqttServer#", listParametersEntity.get(1).getValue());

            codeDeviceEntity.setTextCode(textCode);

            return codeDeviceEntity;
        }catch (Exception e){
            throw e;
        }
    }

    public CodeDeviceEntity replaceUserHash(CodeDeviceEntity codeDeviceEntity, String userHash){
        try {
            String textCode = codeDeviceEntity.getTextCode();
            textCode = textCode.replaceAll("#uniqueHashCode#",userHash);

            codeDeviceEntity.setTextCode(textCode);
            return codeDeviceEntity;
        }catch (Exception e){
            throw e;
        }
    }

    public CodeDeviceEntity replaceSensorParams(List<ParametersEntity> listParametersEntity, CodeDeviceEntity codeDeviceEntity){
        try {
            String textCode = codeDeviceEntity.getTextCode();
            textCode = textCode.replaceAll("#distSensor#", listParametersEntity.get(0).getValue());
            codeDeviceEntity.setTextCode(textCode);

            return codeDeviceEntity;
        }catch (Exception e){
            throw e;
        }
    }

}
