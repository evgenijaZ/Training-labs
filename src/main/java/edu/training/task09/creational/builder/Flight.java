package edu.training.task09.creational.builder;

public class Flight {
    private FlightClass flightClass;

    Flight(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public enum FlightClass {
        FIRST, BUSINESS, ECONOMY
    }
}
