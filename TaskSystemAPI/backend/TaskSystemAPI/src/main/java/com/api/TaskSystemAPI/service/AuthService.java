package com.api.TaskSystemAPI.service;

import com.api.TaskSystemAPI.components.config.security.TokenService;
import com.api.TaskSystemAPI.components.validate.UserValidate;
import com.api.TaskSystemAPI.dto.ResponseDto;
import com.api.TaskSystemAPI.dto.post.AuthPost;
import com.api.TaskSystemAPI.exception.ExceptionGeneric;
import com.api.TaskSystemAPI.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserValidate userValidate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;


    public ResponseDto login(AuthPost authPost) {

        UserModel user = userValidate.findByEmail(authPost.email());

        if(passwordEncoder.matches(authPost.password(), user.getPassword())) {

            String token = tokenService.generateToken(user);
            return new ResponseDto(token);
        }

         throw new ExceptionGeneric("Login error!", "Invalid credentials. Check your password", 401);
    }

}
