package edu.training.task09.behavioral.state;

public class FishermanState implements State {
    @Override
    public void catchPrey() {
        System.out.println("Catched fish");
    }
}
