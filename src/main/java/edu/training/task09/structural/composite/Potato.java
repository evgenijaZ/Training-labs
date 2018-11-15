package edu.training.task09.structural.composite;

public class Potato extends Vegetables {
    protected Potato() {
        super("yellow");
    }

    @Override
    public String getTaste() {
        return "delicate potato";
    }
}
