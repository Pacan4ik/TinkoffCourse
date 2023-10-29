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
            if (freqDict.containsKey(obj)) {
                freqDict.replace(obj, freqDict.get(obj) + 1);
            } else {
                freqDict.put(obj, 1);
            }
        }
        return freqDict;
    }
}
