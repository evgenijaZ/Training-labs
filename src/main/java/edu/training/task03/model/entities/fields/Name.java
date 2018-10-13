package edu.training.task03.model.entities.fields;

public class Name {
    private String firstName;
    private String secondName;
    private String surname;
    private String nickname;

    private Name(Builder builder) {
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.surname = builder.surname;
        this.nickname = builder.nickname;
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

    public String getNickname() {
        return nickname;
    }

    public static class Builder {
        private String firstName;
        private String secondName;
        private String surname;
        private String nickname;

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

        public Builder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Name build() {
            return new Name(this);
        }
    }
}
