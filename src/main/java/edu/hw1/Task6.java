package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public class Task6 {
    private final static int[] digArr = new int[4];
    final static int KAPREKARSCONST = 6174;

    public static int countK(int n) {
        if (n < 1000 || n > 9999) {
            return -1;
        }
        int d = n;
        int digital = d % 10;
        boolean isAllDigsTheSame = true;
        for (int i = 0; i < 3; i++) {
            d /= 10;
            if (d % 10 != digital) {
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
        for (int i = 0; i < digArr.length; i++) {
            digArr[i] = num % 10;
            num /= 10;
        }
        Arrays.sort(digArr);
        int smaller = 0;
        int bigger = 0;
        for (int i = 0, pow = 1; i < digArr.length; i++, pow *= 10) {
            smaller += digArr[digArr.length - 1 - i] * pow;
            bigger += digArr[i] * pow;
        }
        return calculateDepth(bigger - smaller, ++depth);
    }
}
