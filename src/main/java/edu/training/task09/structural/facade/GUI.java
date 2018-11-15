package edu.training.task09.structural.facade;

public class GUI {
    private CPU cpu;
    private Memory memory;

    public GUI() {
        CPU cpu = new CPU();
        Memory memory = new Memory(1048756);
    }

    public void start() {
        cpu.run();
    }

    public void finish() {
        cpu.stop();
    }

    public void openTextEditor() {
        memory.allocate(2048);
        cpu.execute("text editor");

    }

    public void saveFile() {
        boolean isSuccessful = memory.allocate(1024);
        if (isSuccessful) System.out.println("File saved");
        else System.out.println("Cannot save file");
    }


}
