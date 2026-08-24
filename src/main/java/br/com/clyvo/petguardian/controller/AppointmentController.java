package br.com.clyvo.petguardian.controller;

import br.com.clyvo.petguardian.dto.request.AppointmentRequest;
import br.com.clyvo.petguardian.dto.response.AppointmentResponse;
import br.com.clyvo.petguardian.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody @Valid AppointmentRequest request) {
        AppointmentResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id) {
        AppointmentResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pet/{petId}")
    @Operation(summary = "Lista todas as consultas de um pet específico")
    public ResponseEntity<List<AppointmentResponse>> findByPetId(@PathVariable Long petId) {
        return ResponseEntity.ok(service.findByPetId(petId));
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponse>> getAll(Pageable pageable) {
        Page<AppointmentResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> update(@PathVariable Long id, @RequestBody @Valid AppointmentRequest request) {
        AppointmentResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}