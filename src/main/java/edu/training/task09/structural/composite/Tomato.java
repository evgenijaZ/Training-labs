package edu.training.task09.structural.composite;

public class Tomato extends Vegetables {
    protected Tomato() {
        super("red");
    }

    @Override
    public String getTaste() {
        return "sour tomato";
    }
}
