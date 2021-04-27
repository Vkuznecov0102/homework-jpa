package ru.itsjava.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.dao.UserRepository;
import ru.itsjava.domains.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public long countUserByName(String fio) {
        return repository.countUserByName(fio);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return repository.findById(id);
    }

    @Override
    public void insertUser(User user) {
        repository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void updateUser(long id, String fio) {
        repository.updateUser(id, fio);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
