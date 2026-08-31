package br.com.clyvo.vitalia.repository;

import br.com.clyvo.vitalia.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AccountRepository extends JpaRepository<AppUser, Long> {
    UserDetails findByEmail(String email);
}
