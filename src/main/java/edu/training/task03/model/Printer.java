package edu.training.task03.model;

import edu.training.task03.view.View;

public class Printer {

    public void printRecords(RecordBook book, View view) {
        view.printMessage(View.DELIMITER);
        for (Record record :
                book.getRecords()) {
            view.printMessage(record.toString());
            view.printMessage(View.DELIMITER);
        }

    }
}
