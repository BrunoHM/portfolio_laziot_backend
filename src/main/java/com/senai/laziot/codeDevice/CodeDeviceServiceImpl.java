package com.senai.laziot.codeDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeDeviceServiceImpl implements CodeDeviceService {

    @Autowired
    private CodeDeviceRepository codeDeviceRepository;

    @Override
    public CodeDeviceEntity getEmissorCode() {
        try {
            return codeDeviceRepository.getCodeDeviceEntityByTypeDeviceEquals("emissor");
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public CodeDeviceEntity getReceptorCode() {
        try {
            return codeDeviceRepository.getCodeDeviceEntityByTypeDeviceEquals("receptor");
        }catch (Exception e){
            throw e;
        }
    }

}
