package br.com.clyvo.vitalia.controller;

import br.com.clyvo.vitalia.dto.request.VeterinarianRequest;
import br.com.clyvo.vitalia.dto.response.VeterinarianResponse;
import br.com.clyvo.vitalia.service.VeterinarianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("veterinarians")
@RequiredArgsConstructor
public class VeterinarianController {

    private final VeterinarianService service;

    @PostMapping
    public ResponseEntity<VeterinarianResponse> create(@RequestBody @Valid VeterinarianRequest request) {
        VeterinarianResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<VeterinarianResponse>> findAll(Pageable pageable) {
        Page<VeterinarianResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<VeterinarianResponse> findById(@PathVariable Long id) {
        VeterinarianResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<VeterinarianResponse> update(@PathVariable Long id, @RequestBody @Valid VeterinarianRequest request) {
        VeterinarianResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}