package br.com.clyvo.vitalia.controller;

import br.com.clyvo.vitalia.dto.request.AccountRequest;
import br.com.clyvo.vitalia.dto.response.AccountResponse;
import br.com.clyvo.vitalia.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    public ResponseEntity<AccountResponse> create(@RequestBody @Valid AccountRequest request) {
        AccountResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<AccountResponse>> findAll(Pageable pageable) {
        Page<AccountResponse> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable Long id) {
        AccountResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable Long id, @RequestBody @Valid AccountRequest request) {
        AccountResponse response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}