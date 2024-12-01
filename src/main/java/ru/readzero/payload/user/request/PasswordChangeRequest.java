package ru.readzero.payload.user.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PasswordChangeRequest(
        @NotEmpty(message = "Old password can't be empty")
        String oldPassword,
        @NotEmpty(message = "New password can't be empty")
        String newPassword,
        @NotEmpty(message = "Confirm password can't be empty")
        String confirmPassword
) {
}
