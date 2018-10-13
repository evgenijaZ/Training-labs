package edu.training.task03.controller;

public enum PropertyKey {
    FIRST_NAME("first name", "name"),
    SECOND_NAME("second name", "name"),
    SURNAME("surname", "name"),
    NICKNAME("nickname", "nickname"),
    COMMENT("comment", "comment"),
    GROUP("group", "group"),
    HOME_PHONE("home phone", "mobile-phone.regexp"),
    MOBILE_PHONE("mobile phone", "mobile-phone.regexp"),
    ADDITIONAL_MOBILE_PHONE("second mobile phone (additional)", "additional-phone.regexp"),
    EMAIL("email", "email"),
    SKYPE("skype", "nickname"),
    ZIP("zip", "zip"),
    CITY("city", "city"),
    STREET("street", "street"),
    HOUSE_NUMBER("house number", "house"),
    FLAT_NUMBER("flat number", "flat");

    public final String name;
    public final String key;

    PropertyKey(String name, String key) {
        this.name = name;
        this.key = key;
    }
}
