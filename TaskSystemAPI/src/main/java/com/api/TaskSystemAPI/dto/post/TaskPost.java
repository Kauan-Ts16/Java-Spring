package com.api.TaskSystemAPI.dto.post;

import jakarta.validation.constraints.NotBlank;

public record TaskPost(@NotBlank String description) {
}
