package com.senai.laziot.user.useCase;

import com.senai.laziot.user.DTO.UserLoginDTO;
import com.senai.laziot.user.UserService;
import com.senai.laziot.validators.NewUserValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLoginUseCase {

    @Autowired
    private UserService userService;

    public boolean validate(UserLoginDTO userLoginDTO) throws NewUserValidator {
        java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
        String[] parts = userLoginDTO.getUserTokenJWT().split("\\.");

        String payloadJson = new String(decoder.decode(parts[1]));
        JSONObject jsonObject = new JSONObject(payloadJson);
        String userAcessToken = jsonObject.getString("sub");

        boolean userExists = userService.verifyTokenUser(userAcessToken);

        if(!userExists) {
            userExists = userService.createUserService(jsonObject);
        }

        return userExists;
    }
}
