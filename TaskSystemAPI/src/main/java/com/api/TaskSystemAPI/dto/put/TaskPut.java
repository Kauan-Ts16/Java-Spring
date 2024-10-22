package com.api.TaskSystemAPI.dto.put;

import jakarta.validation.constraints.NotBlank;

public record TaskPut(@NotBlank String description) {
}
