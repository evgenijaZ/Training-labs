package edu.training.task09.structural.composite;

public class Cabbage extends Vegetables {
    protected Cabbage() {
        super("white");
    }

    @Override
    public String getTaste() {
        return "cabbage";
    }
}
