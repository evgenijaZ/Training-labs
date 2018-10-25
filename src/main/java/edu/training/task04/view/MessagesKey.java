package edu.training.task04.view;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessagesKey {
    INPUT_MESSAGE("input"),
    WRONG_INPUT_MESSAGE("warning.wrong-input");

    public final String value;

    MessagesKey(String key) {
        var bundle = ResourceBundle.getBundle("application");
        var localeString = bundle.getString("locale");
        var locale = new Locale(localeString);
        var messagesBundle = ResourceBundle.getBundle("messages", locale);
        this.value = messagesBundle.getString(key);
    }

    @Override
    public String toString() {
        return value;
    }
}
