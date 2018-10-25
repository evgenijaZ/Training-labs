package edu.training.task05;

public class TextWindow extends Window {
    private String text;
    private Color color;

    public TextWindow() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TextWindow{" +
                "text='" + text + '\'' +
                ", color=" + color +
                '}';
    }
}
