package br.com.clyvo.vitalia.repository;

import br.com.clyvo.vitalia.entity.Pet;
import br.com.clyvo.vitalia.enums.CurrentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Pet> findBySpeciesIgnoreCase(String species, Pageable pageable);
    Page<Pet> findByCurrentStatus(CurrentStatus currentStatus, Pageable pageable);

    Page<Pet> findByResponsibleId(Long responsibleId, Pageable pageable);
}