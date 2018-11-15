package edu.training.task09.structural.decorator;

public class PizzaDecorator extends Pizza {
    private Pizza pizza;
    private Component component;

    public PizzaDecorator(Pizza pizza) {
        super(pizza.getName());
        this.pizza = pizza;
    }

    @Override
    public int getPrice() {
        return pizza.getPrice() + component.price;
    }

    @Override
    public String getName() {
        return pizza.getName() + ", " + component.name();
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
