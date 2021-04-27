package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.dao.EmailRepository;
import ru.itsjava.domains.Email;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final EmailRepository repository;

    @Override
    public long countEmailByAddress(String address) {
        return repository.countByAddress(address);
    }

    @Override
    public Optional<Email> getEmailById(long id) {
        return repository.findById(id);
    }

    @Override
    public void insertEmail(Email email) {
        repository.saveAndFlush(email);
    }

    @Override
    @Transactional
    public void updateEmail(long id, String address) {
        repository.updateEmail(id, address);
    }

    @Override
    @Transactional
    public void deleteEmail(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Email> findAll() {
        return repository.findAll();
    }
}