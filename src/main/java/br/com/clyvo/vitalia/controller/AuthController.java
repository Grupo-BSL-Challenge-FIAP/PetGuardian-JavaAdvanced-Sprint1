package br.com.clyvo.vitalia.controller;

import br.com.clyvo.vitalia.dto.request.LoginRequest;
import br.com.clyvo.vitalia.dto.request.RegisterRequest;
import br.com.clyvo.vitalia.dto.response.LoginResponse;
import br.com.clyvo.vitalia.dto.response.MeResponse;
import br.com.clyvo.vitalia.dto.request.MobileRegisterRequest;
import br.com.clyvo.vitalia.entity.Account;
import br.com.clyvo.vitalia.enums.Role;
import br.com.clyvo.vitalia.repository.AccountRepository;
import br.com.clyvo.vitalia.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var account = (Account) auth.getPrincipal();
        var token = tokenService.generateToken(account);

        return ResponseEntity.ok(new LoginResponse(token, account.getId(), account.getRole()));
    }

    @GetMapping("/me")
    public ResponseEntity<MeResponse> getCurrentUser(@AuthenticationPrincipal Account account) {
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        MeResponse response = new MeResponse(
                account.getId(),
                account.getEmail(),
                account.getRole()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/vet")
    public ResponseEntity<Void> registerVet(@RequestBody @Valid RegisterRequest data) {
        if (this.accountRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        LocalDateTime now = LocalDateTime.now();
        Account newAccount = Account.builder()
                .fullName(data.fullName())
                .email(data.email())
                .password(encryptedPassword)
                .status("ACTIVE")
                .role(data.role())
                .createdAt(now)
                .updatedAt(now)
                .build();

        this.accountRepository.save(newAccount);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> register(@RequestBody @Valid MobileRegisterRequest data) {

        if (this.accountRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        LocalDateTime now = LocalDateTime.now();
        Account newAccount = Account.builder()
                .fullName(data.fullName())
                .email(data.email())
                .password(encryptedPassword)
                .phone(data.phoneNumber())
                .status("ACTIVE")
                .role(Role.TUTOR)
                .createdAt(now)
                .updatedAt(now)
                .build();

        this.accountRepository.save(newAccount);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
