package com.signalops.backend.controller;

import com.signalops.backend.dto.IncidentResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @GetMapping
    public List<IncidentResponse> getIncidents() {
        return List.of(
                new IncidentResponse(
                        1L,
                        "High error rate on auth service",
                        "HIGH",
                        "INVESTIGATING",
                        Instant.now().minusSeconds(1800)
                ),
                new IncidentResponse(
                        2L,
                        "Latency spike on metrics pipeline",
                        "MEDIUM",
                        "MONITORING",
                        Instant.now().minusSeconds(5400)
                ),
                new IncidentResponse(
                        3L,
                        "Worker backlog increased",
                        "LOW",
                        "OPEN",
                        Instant.now().minusSeconds(10800)
                )
        );
    }
}