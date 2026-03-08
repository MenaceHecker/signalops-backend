package com.signalops.backend.dto;

import java.time.Instant;

public class IncidentResponse {
    private Long id;
    private String title;
    private String severity;
    private String status;
    private Instant createdAt;

    public IncidentResponse(Long id, String title, String severity, String status, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.severity = severity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSeverity() {
        return severity;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}