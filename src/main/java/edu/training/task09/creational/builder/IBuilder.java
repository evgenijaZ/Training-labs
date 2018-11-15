package edu.training.task09.creational.builder;

public interface IBuilder {
    Tour build();

    public IBuilder setHotelName(String name);

    public IBuilder setHotelStars(int stars);

    public IBuilder setHotelType(Accommodation.HotelType type);

    public IBuilder setHotelNumberOfGuests(int numberOfGuests);

    public IBuilder setHotelRoomWithSharedBathroom(boolean withSharedBathroom);

    public IBuilder setFlightClass(Flight.FlightClass flightClass);

    public IBuilder setTransfer(Transfer transfer);

    public IBuilder setInsurance(boolean insurance);
}
