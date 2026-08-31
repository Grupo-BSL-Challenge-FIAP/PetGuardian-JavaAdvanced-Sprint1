package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.dto.request.ClinicalHistoryRequest;
import br.com.clyvo.vitalia.dto.response.ClinicalHistoryResponse;
import br.com.clyvo.vitalia.entity.ClinicalHistory;
import br.com.clyvo.vitalia.repository.ClinicalHistoryRepository;
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
public class ClinicalHistoryService {

    private final ClinicalHistoryRepository repository;
    private final PetRepository petRepository;

    public ClinicalHistoryResponse create(ClinicalHistoryRequest request) {
        if (!petRepository.existsById(request.petId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado");
        }

        ClinicalHistory history = ClinicalHistory.builder()
                .petId(request.petId())
                .veterinarianId(request.veterinarianId())
                .appointmentId(request.appointmentId())
                .diagnosis(request.diagnosis())
                .observations(request.observations())
                .treatment(request.treatment())
                .recordDate(LocalDateTime.now())
                .build();

        return toResponse(repository.save(history));
    }

    public Page<ClinicalHistoryResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public ClinicalHistoryResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro clínico não encontrado"));
    }

    public List<ClinicalHistoryResponse> findByPet(Long petId) {
        return repository.findByPetIdOrderByRecordDateDesc(petId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ClinicalHistoryResponse update(Long id, ClinicalHistoryRequest request) {
        ClinicalHistory history = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro clínico não encontrado"));

        history.setVeterinarianId(request.veterinarianId());
        history.setAppointmentId(request.appointmentId());
        history.setDiagnosis(request.diagnosis());
        history.setObservations(request.observations());
        history.setTreatment(request.treatment());

        return toResponse(repository.save(history));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro clínico não encontrado");
        }
        repository.deleteById(id);
    }

    private ClinicalHistoryResponse toResponse(ClinicalHistory history) {
        return new ClinicalHistoryResponse(
                history.getId(),
                history.getPetId(),
                history.getVeterinarianId(),
                history.getAppointmentId(),
                history.getRecordDate(),
                history.getDiagnosis(),
                history.getObservations(),
                history.getTreatment()
        );
    }
}
