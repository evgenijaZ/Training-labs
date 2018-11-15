package edu.training.task09.structural.adapter;

class Data {
    private byte[] digits;

    Data(byte[] digits) {
        this.digits = digits;
    }

    Data convert() {
        for (byte digit : digits) {
            digit ^= 1;
        }
        return this;
    }
}
