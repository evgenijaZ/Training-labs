package edu.training.task05;

public class Window {
    private int left;
    private int top;
    private int right;
    private int bottom;
    private State state;

    public Window() {
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    @Marked
    public void open() {
        if (state != State.OPEN)
            state = State.OPEN;
        System.out.println("Window is opened");
    }

    @Marked
    public void close() {
        if (state != State.CLOSED)
            state = State.CLOSED;
        System.out.println("Window is closed");
    }

    @Override
    public String toString() {
        return "Window{" +
                "left=" + left +
                ", top=" + top +
                ", right=" + right +
                ", bottom=" + bottom +
                '}';
    }
}
