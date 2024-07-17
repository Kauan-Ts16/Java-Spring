package com.api.ParkingControlAPI.dto.post;

import jakarta.validation.constraints.NotBlank;

public record ParkingSpotPost(
        @NotBlank String parkingSpotNumber, @NotBlank String licensePlateCar, @NotBlank String brandCar,
        @NotBlank String modelCar, @NotBlank String colorCar, @NotBlank String responsibleName,
        @NotBlank String apartment, @NotBlank String block) {
}
