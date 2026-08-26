package br.com.clyvo.vitalia.repository;

import br.com.clyvo.vitalia.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByPetIdOrderByCreatedAtDesc(Long petId);
}