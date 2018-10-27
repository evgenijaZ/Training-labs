package edu.training.task03;

import java.util.LinkedList;
import java.util.List;

public class ArrayChallenge implements Challenge {
    @Override
    public int findSum(int[] array) {
        int sum = 0;
        for (int element : array) {
            sum += element;
        }
        return sum;
    }

    @Override
    public int findValueOfMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    @Override
    public int findIndexOfMax(int[] array) {
        int max = array[0];
        int indexMax = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                indexMax = i;
            }
        }
        return indexMax;
    }

    @Override
    public int findValueOfMin(int[] array) {
        int min = array[0];
        int indexMin = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > min) {
                min = array[i];
                indexMin = i;
            }
        }
        return min;
    }

    @Override
    public int findIndexOfMin(int[] array) {
        int min = array[0];
        int indexMin = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > min) {
                min = array[i];
                indexMin = i;
            }
        }
        return indexMin;
    }

    @Override
    public double findTheAverage(int[] array) {
        int sum = 0;
        for (int element : array) {
            sum += element;
        }
        return sum / array.length;
    }

    @Override
    public long countNumberOfEqualToGiven(int[] array, int given) {
        int counter = 0;
        for (int element : array) {
            if (element == given) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public long countZeros(int[] array) {
        int counter = 0;
        for (int element : array) {
            if (element == 0) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public long countNumberOfElementsGreaterThanZero(int[] array) {
        int counter = 0;
        for (int element : array) {
            if (element > 0) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public int[] multiplyByANumber(int[] array, int number) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= number;
        }
        return array;
    }

    @Override
    public int[] addToElementsTheirIndexes(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] += i;
        }
        return array;
    }

    @Override
    public int[] zeroEvenByValue(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) array[i] = 0;
        }
        return array;
    }

    @Override
    public int[] zeroOddByIndex(int[] array) {
        for (int i = 1; i < array.length; i += 2) {
            array[i] = 0;
        }
        return array;
    }

    @Override
    public int findTheFirstPositive(int[] array) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                index = i;
                break;
            }
        }
        if (index == -1)
            throw new IllegalArgumentException("No positive elements in array");
        else
            return array[index];
    }

    @Override
    public int findTheLastNegative(int[] array) {
        int index = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] < 0) {
                index = i;
                break;
            }
        }
        if (index == -1)
            throw new IllegalArgumentException("No negative elements in array");
        else
            return array[index];
    }

    @Override
    public int[] findIndexesOfTheElement(int[] array, int element) {
        List<Integer> indexes = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) indexes.add(i);
        }
        int[] indexesArray = new int[indexes.size()];
        for (int i = 0; i < indexes.size(); i++) {
            indexesArray[i] = indexes.get(i);
        }
        return indexesArray;
    }

    @Override
    public boolean checkForOrderingInAscendingOrder(int[] array) {
        var isOrderedAscending = true;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                isOrderedAscending = false;
                break;
            }
        }
        return isOrderedAscending;
    }

    @Override
    public boolean checkForOrderingInDescendingOrder(int[] array) {
        var isOrderedDescending = true;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                isOrderedDescending = false;
                break;
            }
        }
        return isOrderedDescending;
    }

    @Override
    public int[] cyclicShiftByKPositionsToTheRight(int[] array, int k) {
        var buffer = new int[array.length + k];
        System.arraycopy(array, 0, buffer, k - 1, array.length);
        System.arraycopy(buffer, array.length, buffer, 0, k);
        System.arraycopy(buffer, 0, array, 0, array.length);
        return buffer;
    }

    @Override
    public int[] findElementsWhoseValuesAreUnique(int[] array, int[] otherArray) {
        List<Integer> unique = new LinkedList<>();
        for (int element : array) {
            boolean contains = false;
            for (int otherElement : otherArray) {
                if (element == otherElement) contains = true;
            }
            if (!contains) unique.add(element);
        }
        int[] uniqueArray = new int[unique.size()];
        for (int i = 0; i < unique.size(); i++) {
            uniqueArray[i] = unique.get(i);
        }
        return uniqueArray;
    }

    @Override
    public int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] array = new int[arr1.length + arr2.length];
        //TODO
        return array;
    }

    @Override
    public int[] swapPositiveElements(int[] array) {
        //TODO
        return array;
    }
}
