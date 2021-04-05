package ru.itsjava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itsjava.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select count(u) from User u where u.fio=?1")
    long countUserByName(String fio);

    @Modifying(clearAutomatically = true)
    @Query("update User set fio=:fio where id=:id")
    int updateUser(@Param("id") long id, @Param("fio") String fio);
}
