package edu.tutorial.repository;

import edu.tutorial.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> findAll();

    void save(User user);

    Optional<User> findById(Long id);
}
