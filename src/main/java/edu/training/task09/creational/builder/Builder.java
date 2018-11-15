package edu.training.task09.creational.builder;

public class Builder implements IBuilder {
    private String name;
    private int stars;
    private Accommodation.HotelType type;
    private int numberOfGuests;
    private boolean withSharedBathroom;
    private Flight.FlightClass flightClass;
    private Transfer transfer;
    private boolean insurance;


    @Override
    public Tour build() {
        Accommodation accommodation = new Accommodation(name, stars, type, numberOfGuests, withSharedBathroom);
        Flight flight = new Flight(flightClass);
        return new Tour(accommodation, transfer, insurance, flight);
    }

    @Override
    public IBuilder setHotelName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IBuilder setHotelStars(int stars) {
        this.stars = stars;
        return this;
    }

    @Override
    public IBuilder setHotelType(Accommodation.HotelType type) {
        this.type = type;
        return this;
    }

    @Override
    public IBuilder setHotelNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
        return this;
    }

    @Override
    public IBuilder setHotelRoomWithSharedBathroom(boolean withSharedBathroom) {
        this.withSharedBathroom = withSharedBathroom;
        return this;
    }

    @Override
    public IBuilder setFlightClass(Flight.FlightClass flightClass) {
        this.flightClass = flightClass;
        return this;
    }

    @Override
    public IBuilder setTransfer(Transfer transfer) {
        this.transfer = transfer;
        return this;
    }

    @Override
    public IBuilder setInsurance(boolean insurance) {
        this.insurance = insurance;
        return this;
    }
}
