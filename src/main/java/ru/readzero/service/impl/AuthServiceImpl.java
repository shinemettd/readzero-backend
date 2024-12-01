package ru.readzero.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.readzero.entity.user.User;
import ru.readzero.exception.NotFoundException;
import ru.readzero.exception.auth.EmailAlreadyTaken;
import ru.readzero.exception.auth.UsernameAlreadyTaken;
import ru.readzero.payload.auth.request.LoginRequest;
import ru.readzero.payload.auth.request.RegisterRequest;
import ru.readzero.payload.auth.response.LoginResponse;
import ru.readzero.payload.auth.response.RegisterResponse;
import ru.readzero.payload.auth.response.TokenResponse;
import ru.readzero.repository.UserRepository;
import ru.readzero.service.AuthService;
import ru.readzero.util.JwtUtil;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    JwtUtil jwtUtil;
    UserRepository userRepository;

    AuthenticationManager authenticationManager;
    PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        this.checkLogin(registerRequest.username());
        this.checkEmail(registerRequest.email());

        User user = new User();
        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));
        user.setEmail(registerRequest.email());
        user.createUserInfo();

        userRepository.save(user);

        return new RegisterResponse(registerRequest.username());
    }

    private void checkLogin(String username) {
        if (userRepository.existByUsername(username)) {
            throw new UsernameAlreadyTaken("Username is already taken");
        }
    }

    private void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyTaken("Email already taken");
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new NotFoundException("User does not exists"));

        if (user.isBlocked())
            throw new RuntimeException("Account is blocked");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        String jwtToken = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        return new LoginResponse(jwtToken, refreshToken);
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!jwtUtil.validateToken(refreshToken, user)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String newAccessToken = jwtUtil.generateToken(user);

        return new TokenResponse(newAccessToken);
    }
}
