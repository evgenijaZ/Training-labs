package edu.training.task03.model.entities.fields;

public class Contacts {
    private String homePhone;
    private String mobilePhone;
    private String secondMobilePhone;
    private String email;
    private String skype;

    private Contacts(Builder builder) {
        this.homePhone = builder.homePhone;
        this.mobilePhone = builder.mobilePhone;
        this.secondMobilePhone = builder.secondMobilePhone;
        this.email = builder.email;
        this.skype = builder.skype;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getSecondMobilePhone() {
        return secondMobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getSkype() {
        return skype;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", secondMobilePhone='" + secondMobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", skype='" + skype + '\'' +
                '}';
    }

    public static class Builder {
        private String homePhone;
        private String mobilePhone;
        private String secondMobilePhone;
        private String email;
        private String skype;

        public Builder() {
        }

        public Builder setHomePhone(String homePhone) {
            this.homePhone = homePhone;
            return this;
        }

        public Builder setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
            return this;
        }

        public Builder setSecondMobilePhone(String secondMobilePhone) {
            this.secondMobilePhone = secondMobilePhone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setSkype(String skype) {
            this.skype = skype;
            return this;
        }

        public Contacts build(){
            return new Contacts(this);
        }
    }
}
