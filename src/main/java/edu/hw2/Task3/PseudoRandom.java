package edu.hw2.Task3;

public final class PseudoRandom {

    private static final char[] SEED = "BBAB".toCharArray(); //TTFT
    private static int index = 0;

    private PseudoRandom() {
    }

    public static boolean getBool() {
        if (index >= SEED.length - 1) {
            index = 0;
        }
        return ((int) SEED[index++]) % 2 == 0;
    }
}
