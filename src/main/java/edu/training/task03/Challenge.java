package edu.training.task03;

public interface Challenge {
    int findSum(int[] array);

    int findValueOfMax(int[] array);

    int findIndexOfMax(int[] array);

    int findValueOfMin(int[] array);

    int findIndexOfMin(int[] array);

    double findTheAverage(int[] array);

    long countNumberOfEqualToGiven(int[] array, int given);

    long countZeros(int[] array);

    long countNumberOfElementsGreaterThanZero(int[] array);

    int[] multiplyByANumber(int[] array, int number);

    int[] addToElementsTheirIndexes(int[] array);

    int[] zeroEvenByValue(int[] array);

    int[] zeroOddByIndex(int[] array);

    int findTheFirstPositive(int[] array);

    int findTheLastNegative(int[] array);

    int[] findIndexesOfTheElement(int[] array, int element);

    boolean checkForOrderingInAscendingOrder(int[] array);

    boolean checkForOrderingInDescendingOrder(int[] array);

    int[] cyclicShiftByKPositionsToTheRight(int[] array, int k);

    int[] findElementsWhoseValuesAreUnique(int[] array, int[] otherArray);

    int[] mergeSortedArrays(int[] arr1, int[] arr2);

    int[] swapPositiveElements(int[] array);
}
