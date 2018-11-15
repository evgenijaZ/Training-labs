package edu.training.task09.behavioral.observer;

public class Demo {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        ConcreteSubscriber subscriber1 = new ConcreteSubscriber();
        ConcreteSubscriber subscriber2 = new ConcreteSubscriber();

        publisher.subscribe(subscriber1);
        publisher.subscribe(subscriber2);

        NewsPaper paper1 = new NewsPaper("The News Time");
        publisher.notifySubscribers(paper1);

        publisher.unsubscribe(subscriber2);
        NewsPaper paper2 = new NewsPaper("Esquire");
        publisher.notifySubscribers(paper2);
    }
}
