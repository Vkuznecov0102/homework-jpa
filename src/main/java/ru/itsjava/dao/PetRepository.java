package ru.itsjava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itsjava.domains.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("select count(p) from Pet p where p.type=?1")
    long countPetByType(String type);

    @Query("select count(p) from Pet p where p.name=?1")
    long countPetByName(String name);

    @Modifying(clearAutomatically = true)
    @Query("update Pet set name=:name where id=:id")
    int updatePetByName(@Param("id") long id, @Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Query("update Pet set type=:type where id=:id")
    int updatePetByType(@Param("id") long id, @Param("type") String type);
}
