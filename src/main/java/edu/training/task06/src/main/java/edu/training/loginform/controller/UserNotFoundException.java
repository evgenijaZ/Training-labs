package edu.training.loginform.controller;

class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id) {
        super(String.format("User with id %d not found", id));
    }
}
