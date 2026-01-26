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