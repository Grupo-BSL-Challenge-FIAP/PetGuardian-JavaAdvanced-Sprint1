package br.com.clyvo.petguardian.controller;

import br.com.clyvo.petguardian.dto.request.LoginRequest;
import br.com.clyvo.petguardian.dto.response.LoginResponse;
import br.com.clyvo.petguardian.dto.request.RegisterRequest;
import br.com.clyvo.petguardian.entity.Account;
import br.com.clyvo.petguardian.repository.AccountRepository;
import br.com.clyvo.petguardian.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private AccountRepository repository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var account = (Account) auth.getPrincipal();
        var token = tokenService.generateToken(account);

        return ResponseEntity.ok(new LoginResponse(token, account.getId(), account.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest data) {
        if (this.repository.findByEmail(data.email()) != null) {
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

        this.repository.save(newAccount);

        return ResponseEntity.ok().build();
    }
}