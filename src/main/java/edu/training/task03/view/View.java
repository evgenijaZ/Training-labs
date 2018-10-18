package edu.training.task03.view;

import java.util.Locale;
import java.util.ResourceBundle;

public class View {
    private Locale locale;

    public View() {
        var bundle = ResourceBundle.getBundle("application");
        var localeString = bundle.getString("locale");
        locale = new Locale(localeString);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public Locale getLocale() {
        return locale;
    }
}
