package edu.training.task09.behavioral.observer;

public class ConcreteSubscriber implements Subscriber {
    private NewsPaper newsPaper;

    @Override
    public void update(NewsPaper newsPaper) {
        this.newsPaper = newsPaper;
    }
}
