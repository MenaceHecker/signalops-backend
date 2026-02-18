package com.signalops.backend.controller;

import com.signalops.backend.dto.UserProfileResponse;
import com.signalops.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("/me")
    // public UserProfileResponse me(Authentication auth) {
    //     return userService.getMe(auth.getName());
    // }
}
