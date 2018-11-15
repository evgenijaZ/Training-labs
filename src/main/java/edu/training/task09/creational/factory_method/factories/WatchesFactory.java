package edu.training.task09.creational.factory_method.factories;

import edu.training.task09.creational.factory_method.Watches;

public abstract class WatchesFactory {
    abstract Watches makeWatches();

    public Watches getWatches(){
        return this.makeWatches();
    }
}
