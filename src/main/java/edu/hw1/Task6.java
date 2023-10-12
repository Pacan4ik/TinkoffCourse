package edu.hw1;

import java.util.Arrays;

final public class Task6 {
    private Task6() {
    }

    private static final int KAPREKARS_CONST = 6174;
    private static final int DIGITS_IN_KAPREKARS_CONST = Task2.countDigits(KAPREKARS_CONST);
    private static final int MIN_NUM = (int) Math.pow(10, DIGITS_IN_KAPREKARS_CONST - 1);
    private static final int MAX_NUM = ((int) Math.pow(10, DIGITS_IN_KAPREKARS_CONST)) - 1;
    private static final int BASE = 10;
    private static final int[] DIGITS_ARR = new int[DIGITS_IN_KAPREKARS_CONST];

    public static int countK(int n) {
        if (n < MIN_NUM || n > MAX_NUM) {
            return -1;
        }
        int d = n;
        int digital = d % BASE;
        boolean isAllDigsTheSame = true;
        for (int i = 0; i < DIGITS_IN_KAPREKARS_CONST - 1; i++) {
            d /= BASE;
            if (d % BASE != digital) {
                isAllDigsTheSame = false;
                break;
            }
        }
        if (isAllDigsTheSame) {
            return -1;
        }
        return calculateDepth(n, 0);
    }

    private static int calculateDepth(int num, int depth) {
        if (num == KAPREKARS_CONST) {
            return depth;
        }
        int remainder = num;
        for (int i = 0; i < DIGITS_ARR.length; i++) {
            DIGITS_ARR[i] = remainder % BASE;
            remainder /= BASE;
        }
        Arrays.sort(DIGITS_ARR);
        int smaller = 0;
        int bigger = 0;
        for (int i = 0, pow = 1; i < DIGITS_ARR.length; i++, pow *= BASE) {
            smaller += DIGITS_ARR[DIGITS_ARR.length - 1 - i] * pow;
            bigger += DIGITS_ARR[i] * pow;
        }
        return calculateDepth(bigger - smaller, depth + 1);
    }
}
