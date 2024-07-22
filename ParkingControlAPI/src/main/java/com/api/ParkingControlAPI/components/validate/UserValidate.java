package com.api.ParkingControlAPI.components.validate;

import com.api.ParkingControlAPI.dto.post.UserPost;
import com.api.ParkingControlAPI.dto.put.UserPut;
import com.api.ParkingControlAPI.exception.ExceptionGeneric;
import com.api.ParkingControlAPI.model.UserModel;
import com.api.ParkingControlAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserValidate {

    @Autowired
    private UserRepository userRepository;


    public void isValid(UserPost userPost) {
        existsByUsername(userPost.username());
        validPassword(userPost.password());
    }

    public void updateValid(UserPut userPut) {
        verifyExists(userPut.id());
        validPassword(userPut.password());
    }

    public void verifyExists(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ExceptionGeneric("User not exists!", "User with id: "+ id + " not exists", 404);
        }
    }

    public void existsByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new ExceptionGeneric("Username is not valid!", "Username: "+ username + " is already in use.", 400);
        }
    }

    public void validPassword(String password) {
        if (password.length() <= 6) {
            throw new ExceptionGeneric("Password is not valid!", "Password must be longer than 6 characters", 400);
        }
    }

    public UserModel findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ExceptionGeneric("User not found!", "User with id: "+ id +" not found.", 404));
    }

}
