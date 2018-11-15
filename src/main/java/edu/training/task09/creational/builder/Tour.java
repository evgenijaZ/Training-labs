package edu.training.task09.creational.builder;

public class Tour {
    private Accommodation accommodation;
    private Transfer transfer;
    private boolean insurance;
    private Flight flight;

    public Tour(Accommodation accommodation, Transfer transfer, boolean insurance, Flight flight) {
        this.accommodation = accommodation;
        this.transfer = transfer;
        this.insurance = insurance;
        this.flight = flight;
    }
}
