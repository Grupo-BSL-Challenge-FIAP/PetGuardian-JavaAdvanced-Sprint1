package br.com.clyvo.vitalia.dto.response;

import java.time.LocalDateTime;

public record AppUserResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        String status,
        LocalDateTime createdAt
) {}