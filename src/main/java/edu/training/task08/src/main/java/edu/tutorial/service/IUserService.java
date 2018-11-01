package edu.tutorial.service;

import edu.tutorial.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void save(User user);

    List<User> getAll();

    Optional<User> findById(Long id);
}

