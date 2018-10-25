package edu.training.task04.model.fields;

public class Name {
    private String firstName;
    private String secondName;
    private String surname;

    private Name(Builder builder) {
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.surname = builder.surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return surname + " " + firstName.substring(0, 1) + ".";
    }

    public static class Builder {
        private String firstName;
        private String secondName;
        private String surname;

        public Builder() {
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Name build() {
            return new Name(this);
        }
    }
}
