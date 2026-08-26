package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.entity.Account;
import br.com.clyvo.vitalia.repository.AccountRepository;
import br.com.clyvo.vitalia.dto.request.AccountRequest;
import br.com.clyvo.vitalia.dto.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public AccountResponse create(AccountRequest request) {
        Account account = Account.builder()
                .email(request.email())
                .password(request.password())
                .role(request.role())
                .active(request.active())
                .createdAt(LocalDateTime.now())
                .build();

        return toResponse(repository.save(account));
    }

    public Page<AccountResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public AccountResponse findById(Long id) {
        return toResponse(findAccountById(id));
    }

    public AccountResponse update(Long id, AccountRequest request) {
        Account account = findAccountById(id);
        account.setEmail(request.email());
        account.setPassword(request.password());
        account.setRole(request.role());
        account.setActive(request.active());
        return toResponse(repository.save(account));
    }

    public void delete(Long id) {
        repository.delete(findAccountById(id));
    }

    private Account findAccountById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    private AccountResponse toResponse(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getEmail(),
                account.getRole(),
                account.getActive(),
                account.getCreatedAt()
        );
    }
}