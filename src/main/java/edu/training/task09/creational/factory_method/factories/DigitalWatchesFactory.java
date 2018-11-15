package edu.training.task09.creational.factory_method.factories;

import edu.training.task09.creational.factory_method.DigitalWatches;
import edu.training.task09.creational.factory_method.Watches;

public class DigitalWatchesFactory extends WatchesFactory {
    @Override
    Watches makeWatches() {
        return new DigitalWatches();
    }
}
