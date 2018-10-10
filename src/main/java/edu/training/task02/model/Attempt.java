package edu.training.task02.model;

public class Attempt {
    private int inputtedValue;
    private int lowerBound;
    private int upperBound;

    public Attempt(int inputtedValue, int lowerBound, int upperBound) {
        this.inputtedValue = inputtedValue;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getInputtedValue() {
        return inputtedValue;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    @Override
    public String toString() {
        return "Attempt{" +
                "inputted value=" + inputtedValue +
                ", lower bound=" + lowerBound +
                ", upper bound=" + upperBound +
                '}';
    }
}
