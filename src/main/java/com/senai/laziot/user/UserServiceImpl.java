package com.senai.laziot.user;

import com.senai.laziot.exception.UserException;
import com.senai.laziot.utils.HashesUtil;
import com.senai.laziot.validators.NewUserValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private int numTrysCreateUser = 0;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUserService(JSONObject jsonDataObject) throws NewUserValidator {
        boolean createdUser = false;
        if(numTrysCreateUser <= 1) {
            String name = jsonDataObject.getString("given_name");
            String lastName = jsonDataObject.getString("family_name");
            String email = jsonDataObject.getString("email");
            String userToken = jsonDataObject.getString("sub");
            String hashUniqueTokenIOT = jsonDataObject.getString("jti");

            UserEntity user = new UserEntity(name, lastName, userToken, email, hashUniqueTokenIOT);
            if (Optional.ofNullable(userRepository.getUserEntityByHashUniqueCode(hashUniqueTokenIOT)).isEmpty()) {
                userRepository.save(user);
                createdUser = true;
            } else {
                numTrysCreateUser += 1;
                hashUniqueTokenIOT += jsonDataObject.getString("at_hash");
                jsonDataObject.remove("jti");
                jsonDataObject.put("jti", hashUniqueTokenIOT);
                this.createUserService(jsonDataObject);
            }
        } else {
            throw new NewUserValidator(false);
        }
        return createdUser;
    }

    @Override
    public boolean verifyTokenUser(String token) {
        if(Optional.ofNullable(userRepository.getUserEntityByHashPassword(token)).isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public String getUserUniqueCode(String email) throws UserException {
        String returnQuery = userRepository.getUserUniqueCodeByEmail(email);
        if(!Optional.ofNullable(returnQuery).isEmpty()){
            return returnQuery;
        }
        throw new UserException("Failed to fetch the token!", false);
    }
}
