package ru.itsjava.dao.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Pet;
import ru.itsjava.services.PetService;
import ru.itsjava.services.PetServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(PetServiceImpl.class)
public class PetServiceImplTest {

    @Autowired
    private PetService service;

    private final Optional<Pet> pet=Optional.of(new Pet(1L,"cat","Мурзик"));
    private final Optional<Pet> petSecond=Optional.of(new Pet(2L,"dog","Шарик"));
    private final Optional<Pet> petThird=Optional.of(new Pet(3L,"rat","Чучундра"));
    private final Optional<Pet> petFourth=Optional.of(new Pet(4L,"hamster","Жрун"));
    private final Pet objPet=pet.get();
    private final Pet objSecond=petSecond.get();
    private final Pet objThird=petThird.get();
    private final Pet objFourth=petFourth.get();

    @Test
    public void shouldHaveCorrectInsert(){
        service.insertPet(objPet);
        assertEquals(service.getPetById(1L),pet);
    }

    @Test
    public void shouldHaveCorrectUpdateByType(){
        service.insertPet(objPet);
        assertEquals(service.getPetById(1L),pet);
        service.updatePetByType(1L,"dog");
        assertAll(
                ()->        assertEquals(0,service.countPetByType("cat")),
                ()->        assertEquals(1,service.countPetByType("dog"))
        );
    }

    @Test
    public void shouldHaveCorrectUpdateByName(){
        service.insertPet(objPet);
        assertEquals(service.getPetById(1L),pet);
        service.updatePetByName(1L,"Редиска");
        assertAll(
                ()-> assertEquals(0,service.countPetByName("Мурзик")),
                ()-> assertEquals(1,service.countPetByName("Редиска"))
        );
    }

    @Test
    public void shouldHaveCorrectDelete(){
        service.insertPet(objPet);
        assertEquals(service.getPetById(1L),pet);
        assertNotNull(service.getPetById(1L));
        service.deletePet(1L);
        assertEquals(Optional.empty(), service.getPetById(1L));
    }

    @Test
    public void shouldHaveCorrectFindAll(){
        List<Pet> pets= new ArrayList<>(List.of(objPet, objSecond, objThird, objFourth));
        service.insertPet(objPet);
        service.insertPet(objSecond);
        service.insertPet(objThird);
        service.insertPet(objFourth);

        assertEquals(pets,service.findAll());
    }
}
