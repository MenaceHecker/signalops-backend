package com.signalops.backend.dto;

public class UpdateIncidentStatusRequest {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}