package edu.training.task09.structural.composite;

public abstract class Vegetables implements Component {
    private String color;

    protected Vegetables(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
