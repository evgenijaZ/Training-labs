package edu.training.task09.creational.builder;

public class PremiumTourBuilder extends Builder {
    @Override
    public Tour build() {
        Accommodation accommodation = new Accommodation("Hilton", 5, Accommodation.HotelType.BUSINESS, 1, true);
        Flight flight = new Flight(Flight.FlightClass.FIRST);
        return new Tour(accommodation, Transfer.TAXI, true, flight);
    }
}
