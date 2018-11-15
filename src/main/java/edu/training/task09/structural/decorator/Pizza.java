package edu.training.task09.structural.decorator;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    private String name;
    private int price;
    private List<Component> components;

    public Pizza(String name) {
        this.name = name;
        this.price = 0;
        this.components = new ArrayList<>();
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
