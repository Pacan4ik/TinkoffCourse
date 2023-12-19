package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    private Task3() {
    }

    static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> freqDict = new HashMap<>();
        for (T obj : list) {
            freqDict.merge(obj, 1, Integer::sum);
        }
        return freqDict;
    }
}
