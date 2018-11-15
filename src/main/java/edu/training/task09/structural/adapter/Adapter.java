package edu.training.task09.structural.adapter;

public class Adapter implements DVDPlayer {
    Monitor monitor;

    @Override
    public Data connectViaDVI(Data data) {
        data = data.convert();
        return monitor.connectViaSVGA(data);
    }
}
