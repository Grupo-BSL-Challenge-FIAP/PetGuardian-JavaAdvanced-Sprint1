package br.com.clyvo.vitalia.dto.request;

import br.com.clyvo.vitalia.enums.Role;

public record RegisterRequest (String email, String password, Role role){
    
}
