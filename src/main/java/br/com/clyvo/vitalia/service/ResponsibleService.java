package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.dto.request.ResponsibleRequest;
import br.com.clyvo.vitalia.dto.response.ResponsibleResponse;
import br.com.clyvo.vitalia.entity.Account;
import br.com.clyvo.vitalia.entity.Responsible;
import br.com.clyvo.vitalia.repository.AccountRepository;
import br.com.clyvo.vitalia.repository.ResponsibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ResponsibleService {

    private final ResponsibleRepository repository;
    private final AccountRepository accountRepository;

    @CacheEvict(value = "responsibles", allEntries = true)
    public ResponsibleResponse create(ResponsibleRequest request) {
        Account account = findAccountById(request.accountId());

        Responsible responsible = Responsible.builder()
                .fullName(request.fullName())
                .cpf(request.cpf())
                .dateOfBirth(request.dateOfBirth())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .account(account)
                .build();

        return toResponse(repository.save(responsible));
    }

    @Cacheable("responsibles")
    public Page<ResponsibleResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Cacheable(value = "responsibles", key = "#id")
    public ResponsibleResponse findById(Long id) {
        return toResponse(findResponsibleById(id));
    }

    public Page<ResponsibleResponse> findByName(String name, Pageable pageable) {
        return repository.findByFullNameContainingIgnoreCase(name, pageable).map(this::toResponse);
    }

    @CacheEvict(value = "responsibles", allEntries = true)
    public ResponsibleResponse update(Long id, ResponsibleRequest request) {
        Responsible responsible = findResponsibleById(id);
        Account account = findAccountById(request.accountId());

        responsible.setFullName(request.fullName());
        responsible.setCpf(request.cpf());
        responsible.setDateOfBirth(request.dateOfBirth());
        responsible.setPhoneNumber(request.phoneNumber());
        responsible.setAddress(request.address());
        responsible.setAccount(account);

        return toResponse(repository.save(responsible));
    }

    @CacheEvict(value = "responsibles", allEntries = true)
    public void delete(Long id) {
        Responsible responsible = findResponsibleById(id);
        repository.delete(responsible);
    }

    private Responsible findResponsibleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Responsável com id: " + id + " não encontrado!"));
    }

    private Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Account com id: " + id + " não encontrada!"));
    }

    private ResponsibleResponse toResponse(Responsible responsible) {
        return new ResponsibleResponse(
                responsible.getId(),
                responsible.getFullName(),
                responsible.getCpf(),
                responsible.getDateOfBirth(),
                responsible.getPhoneNumber(),
                responsible.getAddress(),
                responsible.getAccount().getId(),
                responsible.getAccount().getEmail()
        );
    }
}