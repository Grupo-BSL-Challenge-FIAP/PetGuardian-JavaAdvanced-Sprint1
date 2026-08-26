package br.com.clyvo.vitalia.controller;

import br.com.clyvo.vitalia.dto.request.ResponsibleRequest;
import br.com.clyvo.vitalia.dto.response.ResponsibleResponse;
import br.com.clyvo.vitalia.service.ResponsibleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("responsibles")
@RequiredArgsConstructor
@Tag(name = "Responsibles", description = "Gerenciamento dos responsáveis pelos pets")
public class ResponsibleController {

    private final ResponsibleService service;

    @PostMapping
    @Operation(summary = "Cadastra um novo responsável")
    public ResponseEntity<ResponsibleResponse> create(@RequestBody @Valid ResponsibleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping
    @Operation(summary = "Lista todos os responsáveis com paginação e ordenação")
    public ResponseEntity<Page<ResponsibleResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um responsável pelo ID")
    public ResponseEntity<ResponsibleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Busca responsáveis por nome (parcial, sem distinção de maiúsculas)")
    public ResponseEntity<Page<ResponsibleResponse>> findByName(
            @RequestParam String name, Pageable pageable) {
        return ResponseEntity.ok(service.findByName(name, pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza os dados de um responsável")
    public ResponseEntity<ResponsibleResponse> update(
            @PathVariable Long id, @RequestBody @Valid ResponsibleRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um responsável do sistema")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}