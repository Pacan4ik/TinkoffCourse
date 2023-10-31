package edu.hw3;

public class Task1 {
    private Task1() {
    }

    private static final int[][] BORDERS = new int[][] {
        {'a', 'z'},
        {'A', 'Z'}
    };

    static String atbashCipher(String string) {
        char[] arr = string.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= BORDERS[0][0] && arr[i] <= BORDERS[0][1]) {
                arr[i] = (char) (BORDERS[0][0] + BORDERS[0][1] - arr[i]);
            } else if (arr[i] >= BORDERS[1][0] && arr[i] <= BORDERS[1][1]) {
                arr[i] = (char) (BORDERS[1][0] + BORDERS[1][1] - arr[i]);
            }
        }
        return new String(arr);
    }

}
