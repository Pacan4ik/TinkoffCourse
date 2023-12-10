package edu.hw7;

import java.math.BigInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {

    static Arguments[] factorials() {
        return new Arguments[] {
            Arguments.of(0, BigInteger.valueOf(1)),
            Arguments.of(1, BigInteger.valueOf(1)),
            Arguments.of(2, BigInteger.valueOf(2)),
            Arguments.of(3, BigInteger.valueOf(6)),
            Arguments.of(10, BigInteger.valueOf(3628800))
        };
    }

    @ParameterizedTest
    @MethodSource("factorials")
    void shouldReturnFactorials(int number, BigInteger expected) {
        Assertions.assertEquals(expected, Task2.getFact(number));
    }

    @Test
    void shouldThrowExceptionThanNumNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.getFact(-3));
    }
}
