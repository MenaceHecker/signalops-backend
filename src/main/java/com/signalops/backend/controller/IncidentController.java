package com.signalops.backend.controller;

import com.signalops.backend.dto.CreateIncidentRequest;
import com.signalops.backend.dto.IncidentResponse;
import com.signalops.backend.dto.UpdateIncidentStatusRequest;
import com.signalops.backend.service.IncidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public List<IncidentResponse> getIncidents() {
        return incidentService.getAllIncidents();
    }

    @PostMapping
    public IncidentResponse createIncident(@RequestBody CreateIncidentRequest req) {
        return incidentService.createIncident(req);
    }

    @PatchMapping("/{id}/status")
    public IncidentResponse updateIncidentStatus(
            @PathVariable Long id,
            @RequestBody UpdateIncidentStatusRequest req
    ) {
        return incidentService.updateIncidentStatus(id, req.getStatus());
    }
}