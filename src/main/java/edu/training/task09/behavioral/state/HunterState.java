package edu.training.task09.behavioral.state;

public class HunterState implements State {
    @Override
    public void catchPrey() {
        System.out.println("Catched duck");
    }
}
