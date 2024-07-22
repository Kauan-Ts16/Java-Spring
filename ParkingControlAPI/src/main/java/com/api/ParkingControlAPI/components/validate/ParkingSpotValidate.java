package com.api.ParkingControlAPI.components.validate;

import com.api.ParkingControlAPI.dto.post.ParkingSpotPost;
import com.api.ParkingControlAPI.exception.ExceptionGeneric;
import com.api.ParkingControlAPI.model.ParkingSpotModel;
import com.api.ParkingControlAPI.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ParkingSpotValidate {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;


    public void isValid(ParkingSpotPost parkingSpotPost) {
        if (existsByLicensePlateCar(parkingSpotPost.licensePlateCar())) {
            throw new ExceptionGeneric("License Plate Car Exists", "License Plate Car already exists in database", 400);
        }

        if (existsByParkingSpotNumber(parkingSpotPost.parkingSpotNumber())) {
            throw new ExceptionGeneric("Parking Spot Number Exists", "Parking Spot Number already exists in database", 400);
        }

        if (existsByApartmentAndBlock(parkingSpotPost.apartment(), parkingSpotPost.block())) {
            throw new ExceptionGeneric("Apartment and Block Exists", "Apartment and Block combination already exists in database", 400);
        }
    }

    public void verifyExists(UUID id) {
        if (!parkingSpotRepository.existsById(id)) {
            throw new ExceptionGeneric("Parking Spot not exists!", "Parking Spot with id: "+ id +" not exists.", 404);
        }
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public ParkingSpotModel findById(UUID id) {
        return parkingSpotRepository.findById(id)
                .orElseThrow(()-> new ExceptionGeneric("Parking Spot not found!", "Parking Spot with id: "+ id +" not found.", 404));
    }

}
