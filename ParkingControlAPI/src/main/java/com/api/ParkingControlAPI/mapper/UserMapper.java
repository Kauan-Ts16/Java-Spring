package com.api.ParkingControlAPI.mapper;

import com.api.ParkingControlAPI.dto.post.UserPost;
import com.api.ParkingControlAPI.model.UserModel;
import org.springframework.beans.BeanUtils;

public class UserMapper {

    public static UserModel toMapper(UserPost input) {
        UserModel output = new UserModel();
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
