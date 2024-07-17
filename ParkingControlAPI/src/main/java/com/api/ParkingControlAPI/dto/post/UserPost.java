package com.api.ParkingControlAPI.dto.post;

import jakarta.validation.constraints.NotBlank;

public record UserPost(@NotBlank String username, @NotBlank String password) {
}
