package com.signalops.backend.controller;

import com.signalops.backend.dto.CreateIncidentRequest;
import com.signalops.backend.dto.IncidentResponse;
import com.signalops.backend.dto.UpdateIncidentStatusRequest;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final List<IncidentResponse> incidents = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(4);

    public IncidentController() {
        incidents.add(new IncidentResponse(
                1L,
                "High error rate on auth service",
                "HIGH",
                "INVESTIGATING",
                Instant.now().minusSeconds(1800)
        ));
        incidents.add(new IncidentResponse(
                2L,
                "Latency spike on metrics pipeline",
                "MEDIUM",
                "MONITORING",
                Instant.now().minusSeconds(5400)
        ));
        incidents.add(new IncidentResponse(
                3L,
                "Worker backlog increased",
                "LOW",
                "OPEN",
                Instant.now().minusSeconds(10800)
        ));
    }

    @GetMapping
    public List<IncidentResponse> getIncidents() {
        return incidents;
    }

    @PostMapping
    public IncidentResponse createIncident(@RequestBody CreateIncidentRequest req) {
        IncidentResponse created = new IncidentResponse(
                idCounter.getAndIncrement(),
                req.getTitle(),
                req.getSeverity(),
                req.getStatus(),
                Instant.now()
        );

        incidents.add(0, created);
        return created;
    }

    @PatchMapping("/{id}/status")
    public IncidentResponse updateIncidentStatus(
            @PathVariable Long id,
            @RequestBody UpdateIncidentStatusRequest req
    ) {
        for (int i = 0; i < incidents.size(); i++) {
            IncidentResponse incident = incidents.get(i);

            if (incident.getId().equals(id)) {
                IncidentResponse updated = new IncidentResponse(
                        incident.getId(),
                        incident.getTitle(),
                        incident.getSeverity(),
                        req.getStatus(),
                        incident.getCreatedAt()
                );

                incidents.set(i, updated);
                return updated;
            }
        }

        throw new RuntimeException("Incident not found");
    }
}