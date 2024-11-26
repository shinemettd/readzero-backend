package ru.readzero.payload.auth.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "Username should be not empty")
        String username,
        @NotEmpty(message = "Password should be not empty")
        String password
) {
}
