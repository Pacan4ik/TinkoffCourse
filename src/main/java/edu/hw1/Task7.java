package edu.hw1;

final public class Task7 {
    private Task7() {
    }

    private enum CharacteristicsInput {
        CORRECTNUM,
        INCORRECTNUM,
        NEEDSWAP
    }

    private static CharacteristicsInput inputCheck(int num, int shift) {
        if (num < 0 && shift != 0) {
            return CharacteristicsInput.INCORRECTNUM;
        }
        if (shift < 0) {
            return CharacteristicsInput.NEEDSWAP;
        }
        return CharacteristicsInput.CORRECTNUM;

    }

    @SuppressWarnings("MissingSwitchDefault")
    public static int rotateRight(int num, int shift) {
        switch (inputCheck(num, shift)) {

            case INCORRECTNUM:
                return -1;
            case NEEDSWAP:
                return rotateLeft(num, -shift);
            case CORRECTNUM:
                break;
        }
        String binStr = Integer.toBinaryString(num);
        int realShift = shift % binStr.length();
        String shiftedStr =
            binStr.substring(binStr.length() - realShift) + binStr.substring(0, binStr.length() - realShift);
        return Integer.parseInt(shiftedStr, 2);
    }

    @SuppressWarnings("MissingSwitchDefault")
    public static int rotateLeft(int num, int shift) {
        switch (inputCheck(num, shift)) {

            case INCORRECTNUM:
                return -1;
            case NEEDSWAP:
                return rotateLeft(num, -shift);
            case CORRECTNUM:
                break;
        }
        String binStr = Integer.toBinaryString(num);
        int realShift = shift % binStr.length();
        String shiftedStr = binStr.substring(realShift) + binStr.substring(0, realShift);
        return Integer.parseInt(shiftedStr, 2);
    }
}
