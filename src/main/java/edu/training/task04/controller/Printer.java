package edu.training.task04.controller;

import edu.training.task04.model.RecordBook;
import edu.training.task04.view.View;

public class Printer {

    public void printRecords(RecordBook book, View view) {
        for (var record :
                book.getRecords()) {
            view.printMessage(record.toString());
        }
    }
}
