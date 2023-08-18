package com.senai.laziot.utils;

import com.senai.laziot.exception.UserException;
import com.senai.laziot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    @Autowired
    private UserService userService;

    public void validate(String userToken) throws UserException {
        if(userService.verifyTokenUser(userToken)){
            throw new UserException("User not found!", true);
        }
    }

}
