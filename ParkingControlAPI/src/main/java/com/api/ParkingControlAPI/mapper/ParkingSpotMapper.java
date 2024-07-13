package com.api.ParkingControlAPI.mapper;

import com.api.ParkingControlAPI.dto.ParkingSpotPost;
import com.api.ParkingControlAPI.dto.ParkingSpotPut;
import com.api.ParkingControlAPI.model.ParkingSpotModel;
import com.api.ParkingControlAPI.service.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

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
