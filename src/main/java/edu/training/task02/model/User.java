package edu.training.task02.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Attempt> attempts;

    public User(String name) {
        this.name = name;
        attempts = new ArrayList<>();
    }

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
