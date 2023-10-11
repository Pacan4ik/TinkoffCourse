package edu.hw1;

final public class Task7 {
    private Task7() {
    }

    public static int rotateRight(int num, int shift) {
        return rotate(num, shift, true);
    }

    public static int rotateLeft(int num, int shift) {
        return rotate(num, shift, false);
    }

    private static int rotate(int num, int shift, boolean isRight) {
        if (num < 0 && shift != 0) {
            return -1;
        }

        boolean right = isRight;
        if (shift < 0) {
            right = !isRight;

        }
        String binStr = Integer.toBinaryString(num);
        int realShift = Math.abs(shift) % binStr.length();

        String shiftedStr = right
            ? binStr.substring(binStr.length() - realShift) + binStr.substring(0, binStr.length() - realShift)
            : binStr.substring(realShift) + binStr.substring(0, realShift);

        return Integer.parseInt(shiftedStr, 2);
    }
}
