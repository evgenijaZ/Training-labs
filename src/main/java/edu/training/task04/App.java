package edu.training.task04;

import edu.training.task04.controller.Controller;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        var controller = new Controller();
        controller.newRecord();
        controller.printRecordBook();
    }
}
