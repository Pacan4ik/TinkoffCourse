package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final public class Task7 {
    private Task7() {
    }

    private static final int BASE = 10;

    private static int toBin(int n) {
        int res = 0;
        int pow = 1;
        int num = n;
        while (num > 0) {
            res += (num % 2) * pow;
            pow *= BASE;
            num /= 2;
        }
        return res;
    }

    private static List<Boolean> binToBoolList(int binNum) {
        List<Boolean> nums = new ArrayList<>();
        int remainder = binNum;
        while (remainder != 0) {
            nums.add(remainder % BASE == 1);
            remainder /= BASE;
        }
        return nums;
    }

    public static int rotateRight(int n, int k) {
        if (k < 0) {
            return rotateLeft(n, -k);
        }
        int bin = toBin(n);
        var nums = binToBoolList(bin);
        int res = 0;
        int shift = k % nums.size();
        for (int i = shift, j = 0; j < nums.size(); i++, j++) {
            if (nums.get(i % nums.size())) {
                res += (int) Math.pow(2, j);
            }
        }
        return res;
    }

    public static int rotateLeft(int n, int k) {
        if (k < 0) {
            return rotateRight(n, -k);
        }
        int bin = toBin(n);
        var nums = binToBoolList(bin);
        Collections.reverse(nums);
        int res = 0;
        int shift = k % nums.size();
        for (int i = shift, j = nums.size() - 1; j >= 0; i++, j--) {
            if (nums.get(i % nums.size())) {
                res += (int) Math.pow(2, j);
            }
        }
        return res;
    }
}
