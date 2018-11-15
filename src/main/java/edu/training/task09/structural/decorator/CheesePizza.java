package edu.training.task09.structural.decorator;

class CheesePizza extends PizzaDecorator {
    CheesePizza(Pizza pizza) {
        super(pizza);
        this.setComponent(Component.CHEESE);
    }
}
