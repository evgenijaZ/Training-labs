package edu.tutorial.service;

import edu.tutorial.model.User;
import edu.tutorial.repository.IUserRepository;

import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }
}
