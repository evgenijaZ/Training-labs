package edu.training.task07;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class App {
    public static void main(String[] args) {
        var list = Arrays.asList(4, 5, -6, 4, 5, 3, 4, 2, 4, 5, 7);
        Map<Integer, Integer> map = new TreeMap<>();
        for (var value : list) {
            if (map.containsKey(value))
                map.put(value, map.get(value) + 1);
            else map.put(value, 1);
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
