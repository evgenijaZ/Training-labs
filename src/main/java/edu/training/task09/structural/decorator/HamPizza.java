package edu.training.task09.structural.decorator;

class HamPizza extends PizzaDecorator {
    HamPizza(Pizza pizza) {
        super(pizza);
        this.setComponent(Component.HAM);
    }
}
