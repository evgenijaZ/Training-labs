package edu.training.task09.behavioral.observer;

import java.util.List;

public class Publisher {
    private List<Subscriber> subscribers;

    void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    void notifySubscribers(NewsPaper newsPaper) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(newsPaper);
        }
    }
}
