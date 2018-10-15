package edu.training.task03.controller;

import edu.training.task03.controller.readers.CLIReader;
import edu.training.task03.controller.readers.IReader;
import edu.training.task03.model.Printer;
import edu.training.task03.model.Record;
import edu.training.task03.model.RecordBook;
import edu.training.task03.model.fields.Address;
import edu.training.task03.model.fields.Contacts;
import edu.training.task03.model.fields.Group;
import edu.training.task03.model.fields.Name;
import edu.training.task03.view.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.regex.Pattern;

public class Controller {
    private View view;
    private RecordBook recordBook;

    public Controller() {
        view = new View();
        recordBook = new RecordBook();
    }

    public void newRecord() throws IOException {
        var reader = new CLIReader();

        var record = new Record.Builder()
                .setName(
                        new Name.Builder()
                                .setFirstName(readValue(reader, PropertyKey.FIRST_NAME))
                                .setSecondName(readValue(reader, PropertyKey.SECOND_NAME))
                                .setSurname(readValue(reader, PropertyKey.SURNAME))
                                .build())
                .setNickname(readValue(reader, PropertyKey.NICKNAME))
                .setAddress(
                        new Address.Builder()
                                .setIndex(readValue(reader, PropertyKey.ZIP))
                                .setCity(readValue(reader, PropertyKey.CITY))
                                .setStreet(readValue(reader, PropertyKey.STREET))
                                .setHouseNumber(readValue(reader, PropertyKey.HOUSE_NUMBER))
                                .setFlatNumber(readValue(reader, PropertyKey.FLAT_NUMBER))
                                .build())
                .setComment(readValue(reader, PropertyKey.COMMENT))
                .setGroup(Group.valueOf(readValue(reader, PropertyKey.GROUP).toUpperCase()))
                .setContacts(
                        new Contacts.Builder()
                                .setMobilePhone(readValue(reader, PropertyKey.MOBILE_PHONE))
                                .setHomePhone(readValue(reader, PropertyKey.HOME_PHONE))
                                .setSecondMobilePhone(readValue(reader, PropertyKey.ADDITIONAL_MOBILE_PHONE))
                                .setEmail(readValue(reader, PropertyKey.EMAIL))
                                .setSkype(readValue(reader, PropertyKey.SKYPE))
                                .build())
                .setCreateDate(parseDate(readValue(reader, PropertyKey.CREATION_DATE)))
                .setLastModifyDate(parseDate((readValue(reader, PropertyKey.LAST_MODIFICATION_DATE))))
                .build();

        recordBook.addRecord(record);
    }

    private String readValue(IReader reader, PropertyKey propertyKey) throws IOException {

        var properties = new Properties();

        properties.load(new InputStreamReader(new FileInputStream("src/main/resources/regular-expressions.properties"), "Cp1251"));
        String regEx = properties.getProperty(propertyKey.key + ".regexp");
        String message = properties.getProperty(propertyKey.key + ".description");

        boolean isExcepted;
        String inputted;
        view.printMessage(View.INPUT + propertyKey.name.toUpperCase() + ". " + message);

        while (true) {
            inputted = reader.read();
            isExcepted = check(inputted, regEx);
            if (!isExcepted)
                view.printMessage(View.WRONG_INPUT + message);
            else break;
        }

        return inputted;
    }

    private LocalDate parseDate(String text) {
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(text, formatter);

    }

    private boolean check(String text, String regularExpression) {
        return Pattern.matches(regularExpression, text);
    }

    public void printRecordBook(){
        var printer = new Printer();
        printer.printRecords(recordBook, view);
    }
}
