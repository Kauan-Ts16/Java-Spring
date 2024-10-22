package com.api.TaskSystemAPI.dto.post;

import jakarta.validation.constraints.NotBlank;

public record AuthPost(@NotBlank String email, @NotBlank String password) {
}
