package ru.readzero.service;

import ru.readzero.payload.auth.request.LoginRequest;
import ru.readzero.payload.auth.request.RegisterRequest;
import ru.readzero.payload.auth.response.LoginResponse;
import ru.readzero.payload.auth.response.RegisterResponse;
import ru.readzero.payload.auth.response.TokenResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest request);

    TokenResponse refreshToken(String refreshToken);

}
