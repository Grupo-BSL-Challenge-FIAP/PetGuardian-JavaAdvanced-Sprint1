package br.com.clyvo.vitalia.repository;

import br.com.clyvo.vitalia.entity.Responsible;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible, Long> {
    Page<Responsible> findByFullNameContainingIgnoreCase(String name, Pageable pageable);



    Optional<Responsible> findByAccountId(Long accountId);
}