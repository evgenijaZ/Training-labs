package edu.training.loginform.service;

import edu.training.loginform.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    void save(User user);

    List<User> getAll();

    Optional<User> findById(Long id);
}

