package br.com.clyvo.vitalia.service;

import br.com.clyvo.vitalia.dto.request.PetRequest;
import br.com.clyvo.vitalia.dto.response.PetResponse;
import br.com.clyvo.vitalia.entity.Pet;
import br.com.clyvo.vitalia.entity.Responsible;
import br.com.clyvo.vitalia.entity.Veterinarian;
import br.com.clyvo.vitalia.enums.CurrentStatus;
import br.com.clyvo.vitalia.repository.PetRepository;
import br.com.clyvo.vitalia.repository.ResponsibleRepository;
import br.com.clyvo.vitalia.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository repository;
    private final ResponsibleRepository responsibleRepository;
    private final VeterinarianRepository veterinarianRepository;

    @CacheEvict(value = "pets", allEntries = true)
    public PetResponse create(PetRequest request) {
        Responsible resp = responsibleRepository.findById(request.responsibleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado"));

        Veterinarian vet = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinário não encontrado"));

        Pet pet = Pet.builder()
                .name(request.name())
                .species(request.species())
                .breed(request.breed())
                .gender(request.gender())
                .birthDate(request.birthDate())
                .weight(request.weight())
                .currentStatus(request.currentStatus())
                .responsible(resp)
                .veterinarian(vet)
                .build();

        return toResponse(repository.save(pet));
    }

    @Cacheable("pets")
    public Page<PetResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Cacheable(value = "pets", key = "#id")
    public PetResponse findById(Long id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado"));
        return toResponse(pet);
    }

    public Page<PetResponse> findMyPets(Long accountId, Pageable pageable) {
        Responsible responsible = responsibleRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado para esta conta"));

        return repository.findByResponsibleId(responsible.getId(), pageable)
                .map(this::toResponse);
    }


    public Page<PetResponse> findByName(String name, Pageable pageable) {
        return repository.findByNameContainingIgnoreCase(name, pageable).map(this::toResponse);
    }

    public Page<PetResponse> findBySpecies(String species, Pageable pageable) {
        return repository.findBySpeciesIgnoreCase(species, pageable).map(this::toResponse);
    }

    public Page<PetResponse> findByStatus(CurrentStatus status, Pageable pageable) {
        return repository.findByCurrentStatus(status, pageable).map(this::toResponse);
    }

    @CacheEvict(value = "pets", allEntries = true)
    public PetResponse update(Long id, PetRequest request) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado"));

        Responsible resp = responsibleRepository.findById(request.responsibleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsável não encontrado"));

        Veterinarian vet = veterinarianRepository.findById(request.veterinarianId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veterinário não encontrado"));

        pet.setName(request.name());
        pet.setSpecies(request.species());
        pet.setBreed(request.breed());
        pet.setGender(request.gender());
        pet.setBirthDate(request.birthDate());
        pet.setWeight(request.weight());
        pet.setCurrentStatus(request.currentStatus());
        pet.setResponsible(resp);
        pet.setVeterinarian(vet);

        return toResponse(repository.save(pet));
    }

    @CacheEvict(value = "pets", allEntries = true)
    public void delete(Long id) {
        Pet pet = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet não encontrado"));
        repository.delete(pet);
    }

    private PetResponse toResponse(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getBreed(),
                pet.getBirthDate(),
                pet.getWeight(),
                pet.getCurrentStatus(),
                pet.getResponsible().getFullName(),
                pet.getVeterinarian().getFullName()
        );
    }
}