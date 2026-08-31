package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.entity.Role;
import java.util.Set;

public record MeResponse(
        Long id,
        String email,
        Set<Role> roles
) {}