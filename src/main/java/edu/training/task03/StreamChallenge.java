package edu.training.task03;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

public class StreamChallenge implements Challenge {
    @Override
    public int findSum(int[] array) {
        int sum = Arrays.stream(array).sum();
//        Arrays.stream(array).reduce((i, j) -> i > j ? i : j);
//        Arrays.stream(array).reduce(Integer::sum);
        return sum;
    }

    @Override
    public int findValueOfMax(int[] array) {
        return Arrays.stream(array).max().orElseThrow();
    }

    @Override
    public int findIndexOfMax(int[] array) {
        return IntStream.range(0, array.length)
                .reduce((i, j) -> array[i] > array[j] ? i : j).orElseThrow();
    }

    @Override
    public int findValueOfMin(int[] array) {
        return Arrays.stream(array).min().orElseThrow();
    }

    @Override
    public int findIndexOfMin(int[] array) {
        return IntStream.range(0, array.length)
                .reduce((i, j) -> array[i] < array[j] ? i : j).orElseThrow();
    }

    @Override
    public double findTheAverage(int[] array) {
        return Arrays.stream(array).average().orElseThrow();
    }

    @Override
    public long countNumberOfEqualToGiven(int[] array, int given) {
        return Arrays.stream(array).filter(x -> x == given).count();
    }

    @Override
    public long countZeros(int[] array) {
        return Arrays.stream(array).filter(x -> x == 0).count();
    }

    @Override
    public long countNumberOfElementsGreaterThanZero(int[] array) {
        return Arrays.stream(array).filter(x -> x > 0).count();
    }

    @Override
    public int[] multiplyByANumber(int[] array, int number) {
        return Arrays.stream(array)
                .map(x -> x * number).toArray();
    }

    @Override
    public int[] addToElementsTheirIndexes(int[] array) {
        return IntStream.range(0, array.length)
                .map(i -> array[i] * i).toArray();
    }

    @Override
    public int[] zeroEvenByValue(int[] array) {
        return Arrays.stream(array)
                .map(x -> x % 2 == 0 ? 0 : x).toArray();
    }

    @Override
    public int[] zeroOddByIndex(int[] array) {
        return IntStream.range(0, array.length)
                .map(i -> i % 2 == 0 ? array[i] : 0).toArray();
    }

    @Override
    public int findTheFirstPositive(int[] array) {
        return Arrays.stream(array).filter(x -> x > 0).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No positive elements in array"));
    }

    @Override
    public int findTheLastNegative(int[] array) {
        return Arrays.stream(array).filter(x -> x < 0).boxed().min(Collections.reverseOrder())
                .orElseThrow(() -> new IllegalArgumentException("No positive elements in array"));
    }

    @Override
    public Object[] findIndexesOfTheElement(int[] array, int element) {
        //TODO
        return new Object[0];
    }

    @Override
    public boolean checkForOrderingInAscendingOrder(int[] array) {
        //TODO
        return false;
    }

    @Override
    public boolean checkForOrderingInDescendingOrder(int[] array) {
        //TODO
        return false;
    }

    @Override
    public int[] cyclicShiftByKPositionsToTheRight(int[] array, int k) {
        //TODO
        return new int[0];
    }

    @Override
    public int[] findElementsWhoseValuesAreUnique(int[] array, int[] otherArray) {
        //TODO
        return new int[0];
    }

    @Override
    public int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        //TODO
        return new int[0];
    }

    @Override
    public int[] swapPositiveElements(int[] array) {
        //TODO
        return new int[0];
    }
}
