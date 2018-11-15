package edu.training.task09.creational.builder;

public class EconomyTourBuilder extends Builder {
    @Override
    public Tour build() {
        Accommodation accommodation = new Accommodation("Yellow Hostel House", 2, Accommodation.HotelType.HOSTEL, 1, false);
        Flight flight = new Flight(Flight.FlightClass.ECONOMY);
        return new Tour(accommodation, Transfer.BUS, true, flight);
    }
}
