package edu.training.task09.structural.decorator;

public class Demo {
    public static void main(String[] args) {
        Pizza pizza = new BasePizza("Italian pizza");
        pizza = new CheesePizza(pizza);
        pizza = new TomatoPizza(pizza);
        pizza = new HamPizza(pizza);
        System.out.println(pizza.getName());
        System.out.println(pizza.getPrice());
    }
}
