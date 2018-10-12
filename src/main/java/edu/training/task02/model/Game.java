package edu.training.task02.model;

public class Game {
    private int lowerBound;
    private int upperBound;
    private int hiddenNumber;

    public int makeHiddenNumber() {
        return (int) (Math.random() * (upperBound - lowerBound) - 1) + lowerBound + 1;
    }

    public STATE checkValue(int value) {
        if (lowerBound < value && value < upperBound) {
            if (value == hiddenNumber) return STATE.FINISHED;
            if (value > hiddenNumber) upperBound = value;
            if (value < hiddenNumber) lowerBound = value;
            return STATE.ACCEPTED;
        } else return STATE.NOT_ACCEPTED;
    }

    public enum STATE {
        ACCEPTED, NOT_ACCEPTED, FINISHED
    }

    public void setHiddenNumber() {
        hiddenNumber = makeHiddenNumber();
    }

    public void setBounds(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

      public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
