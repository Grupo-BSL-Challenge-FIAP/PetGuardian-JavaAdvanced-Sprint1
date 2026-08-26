package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.enums.Role;

public record MeResponse(
        Long accountId,
        String email,
        Role role,
        Long responsibleId
) {}