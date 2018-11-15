package edu.training.task09.creational.factory_method.factories;

import edu.training.task09.creational.factory_method.MechanicalWatches;
import edu.training.task09.creational.factory_method.Watches;

public class MechanicalWatchesFactory extends  WatchesFactory{
    @Override
    Watches makeWatches() {
        return new MechanicalWatches();
    }
}
