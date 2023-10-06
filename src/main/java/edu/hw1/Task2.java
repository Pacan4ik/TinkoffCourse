package edu.hw1;

public class Task2 {

    private static final int DIVIDER = 10;

    private Task2() {
    }

    public static int countDigits(int num) {
        int i = 0;
        int remainder = num;
        do {
            remainder /= DIVIDER;
            i++;
        } while (remainder != 0);
        return i;
    }
}
