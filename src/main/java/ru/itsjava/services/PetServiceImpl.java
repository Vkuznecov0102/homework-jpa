package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.dao.PetRepository;
import ru.itsjava.domains.Pet;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {

    private final PetRepository repository;

    @Override
    @Transactional
    public long countPetByType(String type) {
        return repository.countPetByType(type);
    }

    @Override
    @Transactional
    public long countPetByName(String name) {
        return repository.countPetByName(name);
    }

    @Override
    @Transactional
    public Optional<Pet> getPetById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void insertPet(Pet pet) {
        repository.saveAndFlush(pet);
    }

    @Override
    @Transactional
    public void updatePetByType(long id, String type) {
        repository.updatePetByType(id, type);
    }

    @Override
    @Transactional
    public void updatePetByName(long id, String name) {
        repository.updatePetByName(id, name);
    }

    @Override
    @Transactional
    public void deletePet(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Pet> findAll() {
        return repository.findAll();
    }
}