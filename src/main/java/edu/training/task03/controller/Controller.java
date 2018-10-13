package edu.training.task03.controller;

import edu.training.task03.controller.readers.CLIReader;
import edu.training.task03.controller.readers.IReader;
import edu.training.task03.model.Record;
import edu.training.task03.model.fields.Address;
import edu.training.task03.model.fields.Contacts;
import edu.training.task03.model.fields.Group;
import edu.training.task03.model.fields.Name;
import edu.training.task03.view.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

public class Controller {
    private View view;

    public Controller() {
        view = new View();
    }

    public void run() throws IOException {

        CLIReader reader = new CLIReader();
        Record record = new Record.Builder()
                .setName(
                        new Name.Builder()
                                .setFirstName(readValue(reader, PropertyKey.FIRST_NAME))
                                .setSecondName(readValue(reader, PropertyKey.SECOND_NAME))
                                .setSurname(readValue(reader, PropertyKey.SURNAME))
                                .setNickname(readValue(reader, PropertyKey.NICKNAME))
                                .build())
                .setAddress(
                        new Address.Builder()
                                .setIndex(readValue(reader, PropertyKey.ZIP))
                                .setCity(readValue(reader, PropertyKey.CITY))
                                .setStreet(readValue(reader, PropertyKey.STREET))
                                .setHouseNumber(readValue(reader, PropertyKey.HOUSE_NUMBER))
                                .setFlatNumber(readValue(reader, PropertyKey.FLAT_NUMBER))
                                .build())
                .setComment(readValue(reader, PropertyKey.COMMENT))
                .setGroup(Group.FAMILY)
                .setContacts(
                        new Contacts.Builder()
                                .setMobilePhone(readValue(reader, PropertyKey.MOBILE_PHONE))
                                .setHomePhone(readValue(reader, PropertyKey.HOME_PHONE))
                                .setSecondMobilePhone(readValue(reader, PropertyKey.ADDITIONAL_MOBILE_PHONE))
                                .setEmail(readValue(reader, PropertyKey.EMAIL))
                                .setSkype(readValue(reader, PropertyKey.SKYPE))
                                .build()
                )
                .setCreateDate(new Date(1999, 12, 3))
                .setLastModifyDate(new Date(2000, 4, 1))
                .build();
        System.out.println(record);
    }

    private String readValue(IReader reader, PropertyKey propertyKey) throws IOException {

        Properties properties = new Properties();

        properties.load(new InputStreamReader(new FileInputStream("src/main/resources/regular-expressions.properties"), "Cp1251"));
        String regEx = properties.getProperty(propertyKey.key+".regexp");
        String message = properties.getProperty(propertyKey.key+".description");

        boolean isExcepted;
        String inputted;
        view.printMessage(View.INPUT + propertyKey.name + ". " + message);

        while (true) {
            inputted = reader.read();
            isExcepted = check(inputted, regEx);
            if (!isExcepted)
                view.printMessage(View.WRONG_INPUT + message);
            else break;
        }

        return inputted;
    }

    private boolean check(String text, String regularExpression) {
        return Pattern.matches(regularExpression, text);
    }
}
