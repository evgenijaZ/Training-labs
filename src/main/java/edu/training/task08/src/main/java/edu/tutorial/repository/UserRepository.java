package edu.tutorial.repository;

import edu.tutorial.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {
    List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(0, "Hannah", "Burton"));
        users.add(new User(1, "Mike", "Nuggens"));
        users.add(new User(2, "David", "Gorphman"));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        for (User user : users) {
            if (user.getId() == id) return Optional.of(user);
        }
        return Optional.empty();
    }
}
