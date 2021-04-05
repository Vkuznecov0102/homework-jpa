package ru.itsjava.services;

import ru.itsjava.domains.Pet;

import java.util.List;
import java.util.Optional;

public interface PetService {
    long countPetByType(String type);

    long countPetByName(String name);

    Optional<Pet> getPetById(long id);

    void insertPet(Pet pet);

    void updatePetByType(long id, String type);

    void updatePetByName(long id, String name);

    void deletePet(long id);

    List<Pet> findAll();
}
