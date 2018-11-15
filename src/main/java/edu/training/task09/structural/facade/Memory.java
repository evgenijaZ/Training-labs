package edu.training.task09.structural.facade;


public class Memory {
    private final int size;
    private int allocated;

    public Memory(int size) {
        this.size = size;
        this.allocated = 0;
    }

    boolean allocate(int count) {
        if (allocated + count > size) return false;
        allocated += count;
        return true;
    }

    void clean() {
        allocated = 0;
    }


}
