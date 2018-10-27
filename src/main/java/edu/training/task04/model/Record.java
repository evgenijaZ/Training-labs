package edu.training.task04.model;

import edu.training.task04.model.fields.Address;
import edu.training.task04.model.fields.Contacts;
import edu.training.task04.model.fields.Group;
import edu.training.task04.model.fields.Name;

import java.time.LocalDate;

public class Record {
    private Name name;
    private String nickname;
    private String comment;
    private Group group;
    private Contacts contacts;
    private Address address;
    private LocalDate creationDate;
    private LocalDate lastModificationDate;

    private Record(Builder builder) {
        this.name = builder.name;
        this.nickname = builder.nickname;
        this.comment = builder.comment;
        this.group = builder.group;
        this.contacts = builder.contacts;
        this.address = builder.address;
        this.creationDate = builder.createDate;
        this.lastModificationDate = builder.lastModifyDate;
    }

    public Name getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getComment() {
        return comment;
    }

    public Group getGroup() {
        return group;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getLastModificationDate() {
        return lastModificationDate;
    }

    @Override
    public String toString() {
        return "Ім'я: " + name +
                "\nНікнейм: " + nickname +
                "\nКоментар: " + comment +
                "\nГрупа: " + group +
                "\nКонтакти: " + contacts +
                "\nАдреса: " + address +
                "\nДата створення: " + creationDate +
                "\nДата останніх змін: " + lastModificationDate;
    }

    public static class Builder {
        private Name name;
        private String nickname;
        private String comment;
        private Group group;
        private Contacts contacts;
        private Address address;
        private LocalDate createDate;
        private LocalDate lastModifyDate;

        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setGroup(Group group) {
            this.group = group;
            return this;
        }

        public Builder setContacts(Contacts contacts) {
            this.contacts = contacts;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setCreateDate(LocalDate createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder setLastModifyDate(LocalDate lastModifyDate) {
            this.lastModifyDate = lastModifyDate;
            return this;
        }

        public Record build() {
            return new Record(this);
        }
    }
}
