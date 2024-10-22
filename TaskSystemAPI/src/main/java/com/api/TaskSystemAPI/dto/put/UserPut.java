package com.api.TaskSystemAPI.dto.put;

import jakarta.validation.constraints.NotBlank;

public record UserPut(@NotBlank String password) {
}
