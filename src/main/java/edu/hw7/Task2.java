package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Task2 {

    private Task2() {
    }

    public static BigInteger getFact(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number can't be negative");
        }
        return IntStream.rangeClosed(1, number).mapToObj(BigInteger::valueOf)
            .parallel() //начинает положительно сказываться где-то при number > 2000
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

}
