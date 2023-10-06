package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task7 {
    private static int toBin(int n) {
        int res = 0;
        int pow = 1;
        while (n > 0) {
            res += (n % 2) * pow;
            pow *= 10;
            n /= 2;
        }
        return res;
    }

    private static List<Boolean> binToBoolList(int binNum) {
        List<Boolean> nums = new ArrayList<>();
        while (binNum != 0) {
            nums.add(binNum % 10 == 1);
            binNum /= 10;
        }
        return nums;
    }

    public static int rotateRight(int n, int k) {
        int bin = toBin(n);
        var nums = binToBoolList(bin);
        int res = 0;
        k %= nums.size();
        for (int i = k, j = 0; j < nums.size(); i++, j++) {
            if (nums.get(i % nums.size())) {
                res += (int) Math.pow(2, j);
            }
        }
        return res;
    }

    public static int rotateLeft(int n, int k) {
        int bin = toBin(n);
        var nums = binToBoolList(bin);
        Collections.reverse(nums);
        int res = 0;
        k %= nums.size();
        for (int i = k, j = nums.size() - 1; j >= 0; i++, j--) {
            if (nums.get(i % nums.size())) {
                res += (int) Math.pow(2, j);
            }
        }
        return res;
    }
}
