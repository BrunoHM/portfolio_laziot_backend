package com.senai.laziot.codeDevice;

import com.senai.laziot.codeDevice.useCases.GetEmissorCodesUseCase;
import com.senai.laziot.codeDevice.useCases.GetReceptorCodesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/code")
public class CodeDeviceController {

    @Autowired
    private GetEmissorCodesUseCase getEmissorCodesUseCase;

    @Autowired
    private GetReceptorCodesUseCase getReceptorCodesUseCase;


    @GetMapping(value = "/emissor",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEmissorCode(@RequestHeader(name = "hash") String userHash){
        return ResponseEntity.ok(getEmissorCodesUseCase.execute(userHash));
    }

    @GetMapping(value = "/receptor",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReceptorCode(@RequestHeader(name = "hash") String userHash){
        return ResponseEntity.ok(getReceptorCodesUseCase.execute(userHash));
    }

}
