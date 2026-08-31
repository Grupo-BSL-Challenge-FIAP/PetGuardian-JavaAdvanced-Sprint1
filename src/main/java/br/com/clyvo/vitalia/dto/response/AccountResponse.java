package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.enums.Role;
import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String fullName,
        String email,
        String phone,
        String status,
        Role role,
        LocalDateTime createdAt
) {}
