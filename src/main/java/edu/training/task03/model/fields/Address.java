package edu.training.task03.model.entities.fields;

public class Address {
    private String index;
    private String city;
    private String street;
    private String houseNumber;
    private String flatNumber;

    private Address(Builder builder) {
        this.index = builder.index;
        this.city = builder.city;
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.flatNumber = builder.flatNumber;
    }

    public String getIndex() {
        return index;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "index='" + index + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", flatNumber='" + flatNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String index;
        private String city;
        private String street;
        private String houseNumber;
        private String flatNumber;

        public Builder() {
        }

        public Builder setIndex(String index) {
            this.index = index;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public Builder setFlatNumber(String flatNumber) {
            this.flatNumber = flatNumber;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
