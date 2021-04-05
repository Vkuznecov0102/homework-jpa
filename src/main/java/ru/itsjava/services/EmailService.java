package ru.itsjava.services;

import ru.itsjava.domains.Email;

import java.util.List;
import java.util.Optional;

public interface EmailService {
    long countEmailByAddress(String address);

    Optional<Email> getEmailById(long id);

    void insertEmail(Email email);

    void updateEmail(long id, String address);

    void deleteEmail(long id);

    List<Email> findAll();
}
