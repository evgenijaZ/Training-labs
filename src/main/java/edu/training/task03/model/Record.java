package edu.training.task03.model;

import edu.training.task03.model.fields.Address;
import edu.training.task03.model.fields.Contacts;
import edu.training.task03.model.fields.Group;
import edu.training.task03.model.fields.Name;

import java.util.Date;

public class Record {
    private Name name;
    private String comment;
    private Group group;
    private Contacts contacts;
    private Address address;
    private Date createDate;
    private Date lastModifyDate;

    private Record(Builder builder) {
        this.name = builder.name;
        this.comment = builder.comment;
        this.group = builder.group;
        this.contacts = builder.contacts;
        this.address = builder.address;
        this.createDate = builder.createDate;
        this.lastModifyDate = builder.lastModifyDate;
    }

    public Name getName() {
        return name;
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

    public Date getCreateDate() {
        return createDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    @Override
    public String toString() {
        return "Record{" +
                "name=" + name +
                ", comment='" + comment + '\'' +
                ", group=" + group +
                ", contacts=" + contacts +
                ", address=" + address +
                ", createDate=" + createDate +
                ", lastModifyDate=" + lastModifyDate +
                '}';
    }

    public static class Builder {
        private Name name;
        private String comment;
        private Group group;
        private Contacts contacts;
        private Address address;
        private Date createDate;
        private Date lastModifyDate;

        public Builder setName(Name name) {
            this.name = name;
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

        public Builder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder setLastModifyDate(Date lastModifyDate) {
            this.lastModifyDate = lastModifyDate;
            return this;
        }

        public Record build(){
            return new Record(this);
        }
    }
}
