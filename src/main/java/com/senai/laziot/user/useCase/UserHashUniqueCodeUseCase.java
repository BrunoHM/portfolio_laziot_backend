package com.senai.laziot.user.useCase;

import com.senai.laziot.exception.UserException;
import com.senai.laziot.user.DTO.UserTokenDTO;
import com.senai.laziot.user.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserHashUniqueCodeUseCase {

    @Autowired
    private UserService userService;

    public UserTokenDTO execute(String email) throws UserException {
        String userUniqueCode = userService.getUserUniqueCode(email);

        UserTokenDTO userTokenDTO = new UserTokenDTO();
        userTokenDTO.setUserEmail(email);
        userTokenDTO.setUserUniqueHashCode(userUniqueCode);

        return userTokenDTO;
    }
}
