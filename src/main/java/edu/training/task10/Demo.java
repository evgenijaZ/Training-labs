package edu.training.task10;

public class Demo {
    @Sum(a = 5, b = 8)
    private int x;

    Demo() {
        Injector.inject(this);
    }

    public int getX() {
        return x;
    }
}
