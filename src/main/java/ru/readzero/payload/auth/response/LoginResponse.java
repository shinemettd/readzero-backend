package ru.readzero.payload.auth.response;

public record LoginResponse(
        String token,
        String refreshToken
) {
}
