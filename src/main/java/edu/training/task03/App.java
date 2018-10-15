package edu.training.task03;

import edu.training.task03.controller.Controller;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        var controller = new Controller();
        controller.newRecord();
        controller.printRecordBook();
    }
}
