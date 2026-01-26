package com.signalops.backend.service;

import com.signalops.backend.config.JwtUtil;
import com.signalops.backend.dto.*;
import com.signalops.backend.model.User;
import com.signalops.backend.repository.UserRepository;
import com.signalops.backend.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    public AuthResponse register(RegisterRequest req) {
        userRepository.findByEmail(req.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already in use");
        });

        User user = new User();
        user.setEmail(req.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}