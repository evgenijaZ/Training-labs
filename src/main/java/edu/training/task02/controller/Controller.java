package edu.training.task02.controller;

import edu.training.task02.model.Attempt;
import edu.training.task02.model.Game;
import edu.training.task02.model.User;
import edu.training.task02.view.View;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private View view;
    private Game game;

    public Controller() {
        view = new View();
        game = new Game();
        game.setBounds(GlobalVariables.PRIMARY_LOWER_BOUND, GlobalVariables.PRIMARY_UPPER_BOUND);
    }

    public void run() {
        game.setHiddenNumber();
        Scanner scanner = new Scanner(System.in);

        User user = initUser(scanner);
        greetUser(user);

        playGame(scanner, user);
        printStatistics(user);
    }

    private User initUser(Scanner scanner) {
        view.printMessage(View.INPUT_NAME);
        String name = scanner.nextLine();
        return new User(name);
    }

    private void greetUser(User user) {
        view.printMessage(View.GREETING + ", " + user.getName());
    }

    private void playGame(Scanner scanner, User user) {
        int inputtedValue;
        boolean isFinished = false;
        do {
            view.printMessage("Input number not including from " + game.getLowerBound() + " to " + game.getUpperBound());
            if (scanner.hasNextInt()) {
                inputtedValue = scanner.nextInt();
                user.addAttempt(inputtedValue, game.getLowerBound(), game.getUpperBound());
                isFinished = checkValue(inputtedValue);
            } else scanner.next();

        } while (!isFinished);
    }

    private boolean checkValue(int inputtedValue) {
        switch (game.checkValue(inputtedValue)) {
            case ACCEPTED:
                view.printMessage(View.ACCEPTED);
                break;
            case NOT_ACCEPTED:
                view.printMessage(View.NOT_ACCEPTED);
                break;
            case FINISHED:
                view.printMessage(View.CONGRATULATIONS);
                return true;
        }
        return false;
    }

    private void printStatistics(User user) {
        view.printMessage(user.toString());
        List<Attempt> statistics = user.getStatistics();
        for (Attempt attempt : statistics) {
            view.printMessage(attempt.toString());
        }
    }
}
