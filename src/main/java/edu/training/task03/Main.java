package edu.training.task03;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int array[] = {3, 8, 7, 1, 2, 2, 7, 5, 9, 4, 9};
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length - 1; i++) {
            for(int j=0; j<array.length-i-1; j++) {
                conditionalSwap(array, j);
            }
        }

        System.out.println(Arrays.toString(array));

    }

    private static void conditionalSwap(int[] array, int i) {
        if (array[i] > array[i + 1]) {
            swap(array, i, i + 1);
        }
    }

    private static void swap(int[] array, int k, int l) {
        int temp = array[k];
        array[k] = array[l];
        array[l] = temp;
    }
}
