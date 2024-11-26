package ru.readzero.payload.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegisterRequest(
        @Email(message = "Email should be valid")
        @NotEmpty(message = "Email should be not empty")
        String email,
        @NotEmpty(message = "Username should be not empty")
        String username,
        @NotEmpty(message = "Password should be not empty")
        String password
) {
}
