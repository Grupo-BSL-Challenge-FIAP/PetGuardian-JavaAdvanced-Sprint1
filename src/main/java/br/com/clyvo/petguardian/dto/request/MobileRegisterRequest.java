package br.com.clyvo.petguardian.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record MobileRegisterRequest(
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String fullName,
        @NotBlank String cpf,
        @NotNull LocalDate dateOfBirth,
        @NotBlank String phoneNumber,
        @NotBlank String address
) {}