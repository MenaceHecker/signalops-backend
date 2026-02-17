package com.signalops.backend.dto;

import com.signalops.backend.util.Role;

import java.time.Instant;

public class UserProfileResponse {
    private Long id;
    private String email;
    private Role role;
    private Instant createdAt;

    public UserProfileResponse(Long id, String email, Role role, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }
    public Instant getCreatedAt() { return createdAt; }
}
