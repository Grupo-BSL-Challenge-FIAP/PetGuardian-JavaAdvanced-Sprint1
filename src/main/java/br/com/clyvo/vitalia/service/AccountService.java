package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.entity.Account;
import br.com.clyvo.vitalia.repository.AccountRepository;
import br.com.clyvo.vitalia.dto.request.AccountRequest;
import br.com.clyvo.vitalia.dto.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        LocalDateTime now = LocalDateTime.now();
        Account account = Account.builder()
                .fullName(request.fullName())
                .email(request.email())
                .password(new BCryptPasswordEncoder().encode(request.password()))
                .phone(request.phone())
                .status("ACTIVE")
                .role(request.role())
                .createdAt(now)
                .updatedAt(now)
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
        account.setFullName(request.fullName());
        account.setEmail(request.email());
        account.setPhone(request.phone());
        account.setRole(request.role());
        account.setUpdatedAt(LocalDateTime.now());
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
                account.getFullName(),
                account.getEmail(),
                account.getPhone(),
                account.getStatus(),
                account.getRole(),
                account.getCreatedAt()
        );
    }
}
