package com.senai.laziot.user;

import com.senai.laziot.exception.UserException;
import com.senai.laziot.validators.NewUserValidator;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    boolean createUserService(JSONObject jsonDataObject) throws NewUserValidator;
    boolean verifyTokenUser(String token);
    String getUserUniqueCode(String email) throws UserException;

}

