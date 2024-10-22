package com.api.TaskSystemAPI.mapper;

import com.api.TaskSystemAPI.dto.post.UserPost;
import com.api.TaskSystemAPI.dto.put.UserPut;
import com.api.TaskSystemAPI.model.UserModel;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static UserModel toMapper(UserPost input) {
        UserModel output = new UserModel();
        BeanUtils.copyProperties(input, output);
        return output;
    }

    public static UserModel toMapper(UserPut input, UserModel output) {
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
