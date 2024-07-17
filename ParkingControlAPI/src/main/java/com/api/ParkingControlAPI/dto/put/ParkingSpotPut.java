package com.api.ParkingControlAPI.dto.put;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ParkingSpotPut(@NotNull UUID id, @NotBlank String responsibleName) {
}
