package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.dto.request.VeterinarianRequest;
import br.com.clyvo.vitalia.dto.response.VeterinarianResponse;
import br.com.clyvo.vitalia.entity.Account;
import br.com.clyvo.vitalia.entity.Veterinarian;
import br.com.clyvo.vitalia.repository.AccountRepository;
import br.com.clyvo.vitalia.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class VeterinarianService {

    private final VeterinarianRepository repository;
    private final AccountRepository accountRepository;

    public VeterinarianResponse create(VeterinarianRequest request) {
        Account account = findAccountById(request.accountId());

        Veterinarian veterinarian = Veterinarian.builder()
                .fullName(request.fullName())
                .cpf(request.cpf())
                .crmv(request.crmv())
                .speciality(request.speciality())
                .account(account)
                .build();

        return toResponse(repository.save(veterinarian));
    }

    public Page<VeterinarianResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public VeterinarianResponse findById(Long id) {
        return toResponse(findVeterinarianById(id));
    }

    public VeterinarianResponse update(Long id, VeterinarianRequest request) {
        Veterinarian vet = findVeterinarianById(id);
        Account account = findAccountById(request.accountId());

        vet.setFullName(request.fullName());
        vet.setCpf(request.cpf());
        vet.setCrmv(request.crmv());
        vet.setSpeciality(request.speciality());
        vet.setAccount(account);

        return toResponse(repository.save(vet));
    }

    public void delete(Long id) {
        repository.delete(findVeterinarianById(id));
    }

    private VeterinarianResponse toResponse(Veterinarian vet) {
        return new VeterinarianResponse(
                vet.getId(),
                vet.getFullName(),
                vet.getCpf(),
                vet.getCrmv(),
                vet.getSpeciality(),
                vet.getAccount().getId(),
                vet.getAccount().getEmail()
        );
    }

    private Account findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account não encontrada"));
    }

    private Veterinarian findVeterinarianById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinário não encontrado"));
    }
}