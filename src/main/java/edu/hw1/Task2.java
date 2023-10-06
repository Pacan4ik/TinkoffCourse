package edu.hw1;

public class Task2 {
    public static int countDigits(int num) {
        int i = 0;
        do {
            num /= 10;
            i++;
        } while (num != 0);
        return i;
    }
}
