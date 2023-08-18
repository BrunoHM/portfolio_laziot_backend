package com.senai.laziot.user;

import com.senai.laziot.exception.UserException;
import com.senai.laziot.user.DTO.UserLoginDTO;
import com.senai.laziot.user.DTO.UserTokenDTO;
import com.senai.laziot.user.useCase.UserHashUniqueCodeUseCase;
import com.senai.laziot.user.useCase.UserLoginUseCase;
import com.senai.laziot.validators.NewUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserLoginUseCase userLoginUseCase;

    @Autowired
    private UserHashUniqueCodeUseCase userHashUniqueCodeUseCase;

    @PostMapping(value = "/login" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<?> verifyUserLogin(@RequestBody UserLoginDTO userLoginDTO) throws NewUserValidator {
        return ResponseEntity.ok(userLoginUseCase.validate(userLoginDTO));
    }

    @GetMapping(value = "/getHashIOT")
    public HttpEntity<?> getHashUniqueCode(@RequestHeader(value = "email") String email) throws UserException {
        return ResponseEntity.ok(userHashUniqueCodeUseCase.execute(email));
    }

}
