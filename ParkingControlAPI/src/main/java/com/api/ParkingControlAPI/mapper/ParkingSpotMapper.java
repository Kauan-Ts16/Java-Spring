package com.api.ParkingControlAPI.mapper;

import com.api.ParkingControlAPI.dto.post.ParkingSpotPost;
import com.api.ParkingControlAPI.dto.put.ParkingSpotPut;
import com.api.ParkingControlAPI.model.ParkingSpotModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ParkingSpotMapper {

    public static ParkingSpotModel toMapper(ParkingSpotPost input) {
        ParkingSpotModel output = new ParkingSpotModel();
        BeanUtils.copyProperties(input, output);
        output.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return output;
    }

    public static ParkingSpotModel toMapper(ParkingSpotPut input, ParkingSpotModel output) {
        BeanUtils.copyProperties(input, output);
        return output;
    }
}
