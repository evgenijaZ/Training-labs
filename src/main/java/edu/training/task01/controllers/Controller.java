package edu.training.task01.controllers;

import edu.training.task01.model.Model;
import edu.training.task01.view.View;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {
    private static final String HELLO_REGULAR_EXPRESSION = "(\\s*)(?i)hello(\\s*)";
    private static final String WORLD_REGULAR_EXPRESSION = "(\\s*)(?i)world!(\\s*)";


    private Model model;
    private View view;

    /**
     * Initializing constructor
     */
    public Controller() {
        model = new Model();
        view = new View();
    }

    /**
     * Interaction with user. Expected entering of two parts of message: 'Hello' and 'world!'
     */
    public void processUser() {
        Scanner scanner = new Scanner(System.in);

        String leftPart = inputLeftMessagePart(scanner).trim().toLowerCase();
        model.setLeftPart(leftPart);

        String rightPart = inputRightMessagePart(scanner).trim().toLowerCase();
        model.setRightPart(rightPart);

        String result = model.getFullMessage();
        view.printMessage(result);
    }

    /**
     * Reading the left part of the message with scanner
     *
     * @param scanner scanner
     * @return read line
     */
    private String inputLeftMessagePart(Scanner scanner) {
        view.printMessage(View.INPUT_HELLO);
        while (true) {
            String line = scanner.nextLine();
            if (!Pattern.matches(HELLO_REGULAR_EXPRESSION, line))
                view.printMessage(View.WARNING_WRONG_INPUT + " " + View.INPUT_HELLO);
            else {
                view.printMessage(View.ACCEPTED);
                return line;
            }
        }
    }

    /**
     * Reading the right part of the message with scanner
     *
     * @param scanner scanner
     * @return read line
     */
    private String inputRightMessagePart(Scanner scanner) {
        view.printMessage(View.INPUT_WORLD);
        while (true) {
            String line = scanner.nextLine();
            if (!Pattern.matches(WORLD_REGULAR_EXPRESSION, line))
                view.printMessage(View.WARNING_WRONG_INPUT + View.INPUT_WORLD);
            else {
                view.printMessage(View.ACCEPTED);
                return line;
            }
        }
    }
}
