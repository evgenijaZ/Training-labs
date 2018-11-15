package edu.training.task09.structural.facade;

public class CPU {
    void run() {
        System.out.println("CPU run");
    }

    void stop() {
        System.out.println("CPU stopped");
    }

    void execute(String process) {
        System.out.println("Executed " + process);
    }
}
