package edu.training.task04.controller.readers;

import java.util.Scanner;

public class CLIReader implements IReader {

    private Scanner scanner;

    public CLIReader() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        String line = null;
        if (scanner.hasNext()) {
            line = scanner.nextLine();
        }
        return line;
    }
}
