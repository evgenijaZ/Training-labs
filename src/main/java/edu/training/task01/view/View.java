package edu.training.task01.view;

public class View {
    public static final String WARNING_WRONG_INPUT = "Warning: wrong input!";
    public static final String INPUT_HELLO = "Input 'Hello'";
    public static final String INPUT_WORLD = "Input 'world!'";
    public static final String ACCEPTED = "Line is accepted";

    /**
     * Prints message
     * @param message text to print
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
