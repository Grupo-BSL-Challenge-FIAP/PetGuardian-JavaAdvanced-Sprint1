package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.dto.request.AppointmentRequest;
import br.com.clyvo.vitalia.dto.response.AppointmentResponse;
import br.com.clyvo.vitalia.entity.*;
import br.com.clyvo.vitalia.enums.AppointmentStatus;
import br.com.clyvo.vitalia.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final PetRepository petRepository;
    private final VeterinarianRepository vetRepository;

    public AppointmentResponse create(AppointmentRequest request) {
        Pet pet = petRepository.findById(request.petId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado"));

        Veterinarian vet = vetRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinário não encontrado"));

        Appointment appointment = Appointment.builder()
                .appointmentDate(request.appointmentDate())
                .reason(request.reason())
                .status(AppointmentStatus.SCHEDULED)
                .pet(pet)
                .veterinarian(vet)
                .build();

        return toResponse(repository.save(appointment));
    }

    public Page<AppointmentResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public AppointmentResponse update(Long id, AppointmentRequest request) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada"));

        appointment.setAppointmentDate(request.appointmentDate());
        appointment.setReason(request.reason());
        appointment.setDiagnosis(request.diagnosis());
        appointment.setRecommendation(request.recommendation());
        appointment.setStatus(request.status());

        return toResponse(repository.save(appointment));
    }

    public AppointmentResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada"));
    }

    public List<AppointmentResponse> findByPetId(Long petId) {
        if (!petRepository.existsById(petId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado");
        }

        return repository.findByPetIdOrderByAppointmentDateDesc(petId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }

    private AppointmentResponse toResponse(Appointment app) {
        return new AppointmentResponse(
                app.getId(), app.getAppointmentDate(), app.getReason(),
                app.getDiagnosis(), app.getRecommendation(), app.getStatus(),
                app.getCreatedAt(), app.getPet().getName(), app.getVeterinarian().getFullName()
        );
    }
}