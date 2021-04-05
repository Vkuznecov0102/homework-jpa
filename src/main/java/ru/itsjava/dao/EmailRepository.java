package ru.itsjava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itsjava.domains.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("select count(e) from Email e where e.address=?1")
    long countByAddress(String address);

    @Modifying(clearAutomatically = true)
    @Query("update Email set address=:address where id=:id")
    int updateEmail(@Param("id") long id, @Param("address") String address);
}