package br.com.clyvo.vitalia.dto.response;

public record VeterinarianResponse(
        Long id,
        String fullName,
        String cpf,
        String crmv,
        String speciality,
        Long accountId,
        String email
) {}