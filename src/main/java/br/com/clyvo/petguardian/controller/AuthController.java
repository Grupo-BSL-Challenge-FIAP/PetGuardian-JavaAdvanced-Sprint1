package br.com.clyvo.petguardian.controller;

import br.com.clyvo.petguardian.dto.request.LoginRequest;
import br.com.clyvo.petguardian.dto.request.RegisterRequest;
import br.com.clyvo.petguardian.dto.response.LoginResponse;
import br.com.clyvo.petguardian.dto.request.MobileRegisterRequest;
import br.com.clyvo.petguardian.entity.Account;
import br.com.clyvo.petguardian.entity.Responsible;
import br.com.clyvo.petguardian.enums.Role;
import br.com.clyvo.petguardian.repository.AccountRepository;
import br.com.clyvo.petguardian.repository.ResponsibleRepository;
import br.com.clyvo.petguardian.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    private ResponsibleRepository responsibleRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var account = (Account) auth.getPrincipal();
        var token = tokenService.generateToken(account);

        return ResponseEntity.ok(new LoginResponse(token, account.getId(), account.getRole()));
    }

    @PostMapping("/register/vet")
    public ResponseEntity<Void> registerVet(@RequestBody @Valid RegisterRequest data) {
        if (this.accountRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Account newAccount = Account.builder()
                .email(data.email())
                .password(encryptedPassword)
                .role(data.role())
                .active(true)
                .createdAt(LocalDateTime.now())
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
        Account newAccount = Account.builder()
                .email(data.email())
                .password(encryptedPassword)
                .role(Role.RESPONSIBLE)
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();

        Account savedAccount = this.accountRepository.save(newAccount);

        Responsible newResponsible = Responsible.builder()
                .fullName(data.fullName())
                .cpf(data.cpf())
                .dateOfBirth(data.dateOfBirth())
                .phoneNumber(data.phoneNumber())
                .address(data.address())
                .account(savedAccount)
                .build();

        this.responsibleRepository.save(newResponsible);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}