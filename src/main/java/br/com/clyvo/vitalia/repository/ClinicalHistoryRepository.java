package br.com.clyvo.vitalia.repository;

import br.com.clyvo.vitalia.entity.ClinicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Long> {
    List<ClinicalHistory> findByPetIdOrderByRecordDateDesc(Long petId);
}
