package edu.hw3;

import java.util.Comparator;

public class Task7 {
    public static class NullComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return Integer.MAX_VALUE;
            }
            if (o2 == null) {
                return Integer.MIN_VALUE + 1;
            }
            return o1.compareTo(o2);
        }
    }
}
