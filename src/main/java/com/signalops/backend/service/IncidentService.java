package com.signalops.backend.service;

import com.signalops.backend.dto.CreateIncidentRequest;
import com.signalops.backend.dto.IncidentResponse;
import com.signalops.backend.model.Incident;
import com.signalops.backend.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public IncidentResponse createIncident(CreateIncidentRequest req) {
        Incident incident = new Incident();
        incident.setTitle(req.getTitle());
        incident.setSeverity(req.getSeverity());
        incident.setStatus(req.getStatus());

        Incident saved = incidentRepository.save(incident);
        return toResponse(saved);
    }

    public IncidentResponse updateIncidentStatus(Long id, String status) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incident not found"));

        incident.setStatus(status);
        Incident updated = incidentRepository.save(incident);

        return toResponse(updated);
    }

    private IncidentResponse toResponse(Incident incident) {
        return new IncidentResponse(
                incident.getId(),
                incident.getTitle(),
                incident.getSeverity(),
                incident.getStatus(),
                incident.getCreatedAt()
        );
    }
}