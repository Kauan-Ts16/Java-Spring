package com.api.TaskSystemAPI.components.validate;

import com.api.TaskSystemAPI.dto.post.UserPost;
import com.api.TaskSystemAPI.dto.put.UserPut;
import com.api.TaskSystemAPI.exception.ExceptionGeneric;
import com.api.TaskSystemAPI.model.UserModel;
import com.api.TaskSystemAPI.repository.TaskRepository;
import com.api.TaskSystemAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserValidate {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    public void isValid(UserPost userPost) {
        existsByEmail(userPost.email());
        validatePassword(userPost.password());
    }

    public void validateDelete(UUID id) {
        verifyExists(id);
        verifyExistsTask(id);
    }

    public void validateUpdate(UUID id, UserPut userPut) {
        verifyExists(id);
        validatePassword(userPut.password());
    }

    public void validatePassword(String password) {
        if (password.length() <= 6) {
            throw new ExceptionGeneric("Password is not valid!", "Password must be longer than 6 characters", 400);
        }
    }

    public void verifyExists(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ExceptionGeneric("User not exists!", "User with id: "+ id + " not exists", 404);
        }
    }

    public void verifyExistsTask(UUID id) {
        if (taskRepository.existsByUserModelUserId(id)) {
            throw new ExceptionGeneric("User deletion error", "Cannot delete user with existing tasks.", 400);
        }
    }

    public void existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ExceptionGeneric("Email is not valid!", "Email: "+ email + " is already in use.", 400);
        }
    }

    public UserModel findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ExceptionGeneric("User not found!", "User with id: "+ id +" not found.", 404));
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ExceptionGeneric("User not found!", "User with email: "+ email + " not found.", 404));
    }

}
