package edu.training.task03.controller;

import edu.training.task03.model.RecordBook;
import edu.training.task03.view.View;

public class Printer {

    public void printRecords(RecordBook book, View view) {
        for (var record :
                book.getRecords()) {
            view.printMessage(record.toString());
        }
    }
}
