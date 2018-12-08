package edu.training.task10;

import java.util.Collections;
import java.util.List;

public class Checker {
    boolean checkSum(List<Integer> numbers, int n) {
        Collections.sort(numbers);
        int size = numbers.size();
        if (n > numbers.get(0) * size || numbers.get(size - 1) * size > n)
            return false;
        if (numbers.contains(n)) return true;
        if (numbers.stream().reduce(Integer::sum).orElseThrow() < n) return false;
        if (numbers.stream().reduce(Integer::sum).orElseThrow() == n) return true;

        return false;
    }
}
