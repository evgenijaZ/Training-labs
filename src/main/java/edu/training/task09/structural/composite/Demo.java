package edu.training.task09.structural.composite;

public class Demo {
    public static void main(String[] args) {
        Salad dish = new Salad();
        dish.add(new Cabbage());
        dish.add(new Tomato());

        Salad potatoSalad = new Salad();
        potatoSalad.add(new Potato());

        dish.add(potatoSalad);

        System.out.println(dish.getTaste());
    }
}
