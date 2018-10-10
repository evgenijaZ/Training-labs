package edu.training.task02.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
        attempts = new ArrayList<>();
    }

    private List<Attempt> attempts;

    public void addAttempt(int inputtedValue, int lowerBound, int upperBound) {
        attempts.add(new Attempt(inputtedValue, lowerBound, upperBound));
    }

    public List<Attempt> getStatistics() {
        return attempts;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", total attempts=" + attempts.size() +
                '}';
    }
}
