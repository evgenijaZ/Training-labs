package edu.training.task09.creational.builder;

class Accommodation {
    private String name;
    private int stars;
    private HotelType type;
    private int numberOfGuests;
    private boolean withSharedBathroom;

    Accommodation(String name, int stars, HotelType type, int numberOfGuests, boolean withSharedBathroom) {
        this.name = name;
        this.stars = stars;
        this.type = type;
        this.numberOfGuests = numberOfGuests;
        this.withSharedBathroom = withSharedBathroom;
    }

    public enum HotelType {
        HOSTEL, BUSINESS, SUITE, RESORT, BED_AND_BREAKFAST
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public HotelType getType() {
        return type;
    }

    public void setType(HotelType type) {
        this.type = type;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public boolean isWithSharedBathroom() {
        return withSharedBathroom;
    }

    public void setWithSharedBathroom(boolean withSharedBathroom) {
        this.withSharedBathroom = withSharedBathroom;
    }
}
