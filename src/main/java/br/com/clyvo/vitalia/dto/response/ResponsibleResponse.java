package br.com.clyvo.vitalia.dto.response;

import java.time.LocalDate;

public record ResponsibleResponse(
        Long id,
        String fullName,
        String cpf,
        LocalDate dateOfBirth,
        String phoneNumber,
        String address,
        Long accountId,
        String accountEmail
) {}