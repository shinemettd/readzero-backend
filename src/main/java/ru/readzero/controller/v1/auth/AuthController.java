package ru.readzero.controller.v1.auth;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readzero.aop.HandleUserMetadata;
import ru.readzero.enums.session.SessionActionType;
import ru.readzero.payload.auth.request.LoginRequest;
import ru.readzero.payload.auth.request.RegisterRequest;
import ru.readzero.payload.auth.response.LoginResponse;
import ru.readzero.payload.auth.response.RegisterResponse;
import ru.readzero.payload.auth.response.TokenResponse;
import ru.readzero.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/register")
    @HandleUserMetadata(actionType = SessionActionType.REGISTER)
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = authService.register(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    @HandleUserMetadata(actionType = SessionActionType.LOGIN)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenResponse> refreshToken(@RequestParam String refreshToken) {
        TokenResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

}
