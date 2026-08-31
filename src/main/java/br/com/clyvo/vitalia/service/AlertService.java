package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.dto.request.AlertRequest;
import br.com.clyvo.vitalia.dto.response.AlertResponse;
import br.com.clyvo.vitalia.entity.Alert;
import br.com.clyvo.vitalia.repository.AlertRepository;
import br.com.clyvo.vitalia.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository repository;
    private final PetRepository petRepository;

    public AlertResponse create(AlertRequest request) {
        if (!petRepository.existsById(request.petId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado");
        }

        Alert alert = Alert.builder()
                .petId(request.petId())
                .alertType(request.alertType())
                .message(request.message())
                .severity(request.severity())
                .status(request.status() != null ? request.status() : "OPEN")
                .createdAt(LocalDateTime.now())
                .build();

        return toResponse(repository.save(alert));
    }

    public Page<AlertResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public AlertResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerta não encontrado"));
    }

    public List<AlertResponse> findByPet(Long petId) {
        return repository.findByPetIdOrderByCreatedAtDesc(petId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlertResponse update(Long id, AlertRequest request) {
        Alert alert = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerta não encontrado"));

        alert.setAlertType(request.alertType());
        alert.setMessage(request.message());
        alert.setSeverity(request.severity());
        alert.setPetId(request.petId());

        if ("RESOLVED".equals(request.status()) && !"RESOLVED".equals(alert.getStatus())) {
            alert.setResolvedAt(LocalDateTime.now());
        }
        if (request.status() != null) {
            alert.setStatus(request.status());
        }

        return toResponse(repository.save(alert));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerta não encontrado");
        }
        repository.deleteById(id);
    }

    private AlertResponse toResponse(Alert alert) {
        return new AlertResponse(
                alert.getId(),
                alert.getAlertType(),
                alert.getMessage(),
                alert.getSeverity(),
                alert.getStatus(),
                alert.getCreatedAt(),
                alert.getResolvedAt(),
                alert.getPetId()
        );
    }
}
