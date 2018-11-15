package edu.training.task09.structural.decorator;

public class TomatoPizza extends PizzaDecorator {
    public TomatoPizza(Pizza pizza) {
        super(pizza);
        this.setComponent(Component.TOMATOES);
    }

}
