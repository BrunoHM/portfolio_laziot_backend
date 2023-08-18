package com.senai.laziot.codeDevice;

import org.springframework.stereotype.Component;

@Component
public interface CodeDeviceService {

    CodeDeviceEntity getEmissorCode();
    CodeDeviceEntity getReceptorCode();

}
