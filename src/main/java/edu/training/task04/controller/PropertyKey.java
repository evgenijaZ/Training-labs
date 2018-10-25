package edu.training.task04.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public enum PropertyKey {
    FIRST_NAME("first name", "name"),
    SECOND_NAME("second name", "name"),
    SURNAME("surname", "name"),
    NICKNAME("nickname", "nickname"),
    COMMENT("comment", "comment"),
    GROUP("group", "group"),
    HOME_PHONE("home phone", "mobile-phone"),
    MOBILE_PHONE("mobile phone", "mobile-phone"),
    ADDITIONAL_MOBILE_PHONE("second mobile phone (additional)", "additional-phone"),
    EMAIL("email", "email"),
    SKYPE("skype", "nickname"),
    ZIP("zip", "zip"),
    CITY("city", "city"),
    STREET("street", "street"),
    HOUSE_NUMBER("house number", "house"),
    FLAT_NUMBER("flat number", "flat"),
    CREATION_DATE("creation date", "date"),
    LAST_MODIFICATION_DATE("last modification date", "date");

    public final String name;
    public final String key;
    public final String regEx;
    public final String description;

    PropertyKey(String name, String key) {
        var bundle = ResourceBundle.getBundle("application");
        var localeString = bundle.getString("locale");
        var locale = new Locale(localeString);

        var regExBundle = ResourceBundle.getBundle("regular-expressions", locale);

        regEx = regExBundle.getString(key + ".regexp");
        description = regExBundle.getString(key + ".description");

        this.name = name;
        this.key = key;
    }
}
