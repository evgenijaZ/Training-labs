package edu.training.task02.model;

public class Game {
    public static int LOWER_BOUND = 0;
    public static int UPPER_BOUND = 100;
    private static int HIDDEN_NUMBER;

    public void makeNumber() {
        HIDDEN_NUMBER = (int) (Math.random() * UPPER_BOUND);
    }

    public STATE checkValue(int value) {
        if (LOWER_BOUND <= value && value <= UPPER_BOUND) {
            if (value == HIDDEN_NUMBER) return STATE.FINISHED;
            if (value > HIDDEN_NUMBER) UPPER_BOUND = value;
            if (value < HIDDEN_NUMBER) LOWER_BOUND = value;
            return STATE.ACCEPTED;
        } else return STATE.NOT_ACCEPTED;
    }

   public enum STATE {
        ACCEPTED, NOT_ACCEPTED, FINISHED
    }
}
