package com.senai.laziot.parameters;

import com.senai.laziot.exception.UserException;
import com.senai.laziot.parameters.DTO.UserParameterDTO;
import com.senai.laziot.parameters.DTO.UserParameterSimplifiedDTO;
import com.senai.laziot.parameters.useCase.GetAllUserParams;
import com.senai.laziot.parameters.useCase.UpdateParametersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/parameters")
public class ParametersController {

    @Autowired
    private GetAllUserParams getAllUserParams;

    @Autowired
    private UpdateParametersUseCase updateParametersUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUserParams(@RequestHeader(name = "token") String userToken) throws UserException {
        return ResponseEntity.ok(getAllUserParams.execute(userToken));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateAllUserParams(@RequestHeader(name = "token") String userToken, @RequestBody List<UserParameterDTO> listUserParameterDTO) throws UserException {
        return ResponseEntity.ok(updateParametersUseCase.execute(userToken, listUserParameterDTO));
    }

}
