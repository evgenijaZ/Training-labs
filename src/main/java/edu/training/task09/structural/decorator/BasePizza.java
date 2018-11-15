package edu.training.task09.structural.decorator;

public class BasePizza extends Pizza {
    BasePizza(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + Component.DOUGH.price + Component.PASTE.price;
    }
}
