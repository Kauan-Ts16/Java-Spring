package com.api.ParkingControlAPI.service;

import com.api.ParkingControlAPI.components.validate.ParkingSpotValidate;
import com.api.ParkingControlAPI.dto.ParkingSpotPost;
import com.api.ParkingControlAPI.dto.ParkingSpotPut;
import com.api.ParkingControlAPI.exception.ExceptionGeneric;
import com.api.ParkingControlAPI.mapper.ParkingSpotMapper;
import com.api.ParkingControlAPI.model.ParkingSpotModel;
import com.api.ParkingControlAPI.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Autowired
    ParkingSpotValidate parkingSpotValidate;


    @Transactional
    public ParkingSpotModel save(ParkingSpotPost parkingSpotPost) {
        parkingSpotValidate.isValid(parkingSpotPost);
        return parkingSpotRepository.save(ParkingSpotMapper.toMapper(parkingSpotPost));
    }

    @Transactional
    public void delete(UUID id) {
        parkingSpotValidate.verifyExists(id);
        parkingSpotRepository.deleteById(id);
    }

    public ParkingSpotModel update(ParkingSpotPut parkingSpotPut) {
        return parkingSpotRepository.save(ParkingSpotMapper.toMapper(parkingSpotPut, findById(parkingSpotPut.id())));
    }

    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }

    public ParkingSpotModel findById(UUID id) {
        return parkingSpotValidate.findById(id);
    }

}
