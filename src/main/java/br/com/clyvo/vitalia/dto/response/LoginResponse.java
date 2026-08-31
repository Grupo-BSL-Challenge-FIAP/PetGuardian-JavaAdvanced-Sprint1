package br.com.clyvo.vitalia.dto.response;

import br.com.clyvo.vitalia.entity.Role;
import java.util.Set;

public record LoginResponse(
        String token,
        Long id,
        Set<Role> roles
) {}