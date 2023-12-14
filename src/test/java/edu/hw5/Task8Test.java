package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    static Arguments[] correctStringsWithOddLength() {
        return new Arguments[] {
            Arguments.of("0"),
            Arguments.of("1"),
            Arguments.of("010"),
            Arguments.of("10101"),
            Arguments.of("1010110"),
            Arguments.of("101011011")

        };
    }

    @ParameterizedTest
    @MethodSource("correctStringsWithOddLength")
    @DisplayName("нечетная длина (Positive)")
    void shouldPassValidationWithOddLength(String string) {
        assertTrue(Task8.isLengthIsOdd(string));
    }

    static Arguments[] incorrectStringsWithOddLength() {
        return new Arguments[] {
            Arguments.of(""),
            Arguments.of("10"),
            Arguments.of("0110"),
            Arguments.of("101101"),
            Arguments.of("10100110"),
            Arguments.of("1010111011"),
            Arguments.of("021"),

        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsWithOddLength")
    @DisplayName("нечетная длина (Negative)")
    void shouldntPassValidationWithOddLength(String string) {
        assertFalse(Task8.isLengthIsOdd(string));
    }

    static Arguments[] correctStringsEvenOdd() {
        return new Arguments[] {
            Arguments.of("0"),
            Arguments.of("10"),
            Arguments.of("010"),
            Arguments.of("1010"),
            Arguments.of("0010110"),
            Arguments.of("10101101")

        };
    }

    @ParameterizedTest
    @MethodSource("correctStringsEvenOdd")
    @DisplayName("начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину (Positive)")
    void shouldPassValidationWithEvenOdd(String string) {
        assertTrue(Task8.isLengthIsOddWhenStartsWithZeroOrEvenWithOne(string));
    }

    static Arguments[] incorrectStringsEvenOdd() {
        return new Arguments[] {
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("01"),
            Arguments.of("110"),
            Arguments.of("0010"),
            Arguments.of("1010110"),
            Arguments.of("00101101"),
            Arguments.of("10201101"),

        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsEvenOdd")
    @DisplayName("начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину (Negative)")
    void shouldntPassValidationWithEvenOdd(String string) {
        assertFalse(Task8.isLengthIsOddWhenStartsWithZeroOrEvenWithOne(string));
    }


    static Arguments[] correctStringsEveryOddIsOne() {
        return new Arguments[] {
            Arguments.of("1"),
            Arguments.of("101"),
            Arguments.of("10111"),
            Arguments.of("101111101"),

        };
    }

    @ParameterizedTest
    @MethodSource("correctStringsEveryOddIsOne")
    @DisplayName("каждый нечетный символ равен 1 (Positive)")
    void shouldPassValidationWithEveryOddIsOne(String string) {
        assertTrue(Task8.isEveryOddIsOne(string));
    }


    static Arguments[] incorrectStringsEveryOddIsOne() {
        return new Arguments[] {
            Arguments.of(""),
            Arguments.of("0"),
            Arguments.of("100"),
            Arguments.of("00111"),
            Arguments.of("10111110100"),

        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsEveryOddIsOne")
    @DisplayName("каждый нечетный символ равен 1 (Negative)")
    void shouldntPassValidationWithEveryOddIsOne(String string) {
        assertFalse(Task8.isEveryOddIsOne(string));
    }


    static Arguments[] correctStringsAmountOfZerosIsMultiplyByThree() {
        return new Arguments[] {
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("000"),
            Arguments.of("101010101010"),
            Arguments.of("10001"),
            Arguments.of("000101101110"),
            Arguments.of("1000000000"),

        };
    }

    @ParameterizedTest
    @MethodSource("correctStringsAmountOfZerosIsMultiplyByThree")
    @DisplayName("количество 0 кратно 3 (Positive)")
    void shouldPassValidationWithAmountOfZeros(String string) {
        assertTrue(Task8.isAmountZerosMultipleOfThree(string));
    }

    static Arguments[] incorrectStringsAmountOfZerosIsMultiplyByThree() {
        return new Arguments[] {
            Arguments.of("0"),
            Arguments.of("0020"),
            Arguments.of("010010"),
            Arguments.of("10101000111"),

        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsAmountOfZerosIsMultiplyByThree")
    @DisplayName("количество 0 кратно 3 (Negative)")
    void shouldntPassValidationWithAmountOfZeros(String string) {
        assertFalse(Task8.isAmountZerosMultipleOfThree(string));
    }
}
