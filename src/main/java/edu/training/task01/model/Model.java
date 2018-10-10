package edu.training.task01.model;

public class Model {
    /**
     * Left message part
     */
    private String leftPart;

    /**
     * Right message part
     */
    private String rightPart;

    /**
     * Generates full message
     *
     * @return full message
     */
    public String getFullMessage() {
        return leftPart + " " + rightPart;
    }

    /*
     * Getters & setters
     */

    public String getLeftPart() {
        return leftPart;
    }

    public void setLeftPart(String leftPart) {
        this.leftPart = leftPart;
    }

    public String getRightPart() {
        return rightPart;
    }

    public void setRightPart(String rightPart) {
        this.rightPart = rightPart;
    }
}
