package edu.training.task09.behavioral.state;

public class Demo {
    public static void main(String[] args) {
        Human human = new Human();
        human.setState(Place.FOREST);
        human.feed();

        human.setState(Place.RIVER);
        human.feed();

        human.setState(Place.GLADE);
        human.feed();

    }
}
