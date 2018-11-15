package edu.training.task09.structural.facade;

public class Client {
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.start();
        gui.openTextEditor();
        gui.saveFile();
        gui.finish();
    }
}
