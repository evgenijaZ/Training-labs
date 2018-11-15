package edu.training.task09.behavioral.state;

public class MushroomPickerState implements State {
    @Override
    public void catchPrey() {
        System.out.println("Catched mushroom");
    }
}
