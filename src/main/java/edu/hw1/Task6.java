package edu.hw1;

import java.util.Arrays;

final public class Task6 {
    private Task6() {
    }

    private final static int KAPREKARSCONST = 6174;
    private final static int DIGITSINKAPREKARSCONST = Task2.countDigits(KAPREKARSCONST);
    private final static int MINNUM = (int) Math.pow(10, DIGITSINKAPREKARSCONST - 1);
    private final static int MAXNUM = ((int) Math.pow(10, DIGITSINKAPREKARSCONST)) - 1;
    private final static int BASE = 10;
    private final static int[] DIGITSARR = new int[DIGITSINKAPREKARSCONST];

    public static int countK(int n) {
        if (n < MINNUM || n > MAXNUM) {
            return -1;
        }
        int d = n;
        int digital = d % BASE;
        boolean isAllDigsTheSame = true;
        for (int i = 0; i < DIGITSINKAPREKARSCONST - 1; i++) {
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
        if (num == KAPREKARSCONST) {
            return depth;
        }
        int remainder = num;
        for (int i = 0; i < DIGITSARR.length; i++) {
            DIGITSARR[i] = remainder % BASE;
            remainder /= BASE;
        }
        Arrays.sort(DIGITSARR);
        int smaller = 0;
        int bigger = 0;
        for (int i = 0, pow = 1; i < DIGITSARR.length; i++, pow *= BASE) {
            smaller += DIGITSARR[DIGITSARR.length - 1 - i] * pow;
            bigger += DIGITSARR[i] * pow;
        }
        return calculateDepth(bigger - smaller, depth + 1);
    }
}
