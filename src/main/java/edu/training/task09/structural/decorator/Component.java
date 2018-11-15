package edu.training.task09.structural.decorator;

public enum Component {
    DOUGH(10),
    PASTE(2),
    CHEESE(5),
    TOMATOES(10),
    HAM(25);

    public final int price;

    Component(int price) {
        this.price = price;
    }
}
