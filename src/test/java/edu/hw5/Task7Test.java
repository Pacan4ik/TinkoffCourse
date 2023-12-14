package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task7Test {
    static Arguments[] correctStringsWithThirdZero(){
        return new Arguments[]{
            Arguments.of("110101"),
            Arguments.of("000"),
            Arguments.of("110111111"),
            Arguments.of("00011")

        };
    }

    @ParameterizedTest
    @MethodSource("correctStringsWithThirdZero")
    @DisplayName("содержит не менее 3 символов, причем третий символ равен 0 (Positive)")
    void shouldPassValidationWithThirdZero(String string){
        assertTrue(Task7.isContainsThirdZero(string));
    }

    static Arguments[] incorrectStringsWithThirdZero(){
        return new Arguments[]{
            Arguments.of("111101"),
            Arguments.of("001"),
            Arguments.of("101111111"),
            Arguments.of("00"),
            Arguments.of("0201110"),
            Arguments.of("1"),
            Arguments.of(""),


        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsWithThirdZero")
    @DisplayName("содержит не менее 3 символов, причем третий символ равен 0 (Negative)")
    void shouldntPassValidationWithThirdZero(String string){
        assertFalse(Task7.isContainsThirdZero(string));
    }



    static Arguments[] correctStringsWithSameStartAndEnd(){
        return new Arguments[]{
            Arguments.of("110101"),
            Arguments.of("000"),
            Arguments.of("101"),
            Arguments.of("01010"),
            Arguments.of("000110"),
            Arguments.of("0"),
            Arguments.of("1"),

        };
    }

    @ParameterizedTest
    @MethodSource("correctStringsWithSameStartAndEnd")
    @DisplayName("начинается и заканчивается одним и тем же символом (Positive)")
    void shouldntPassValidationWithSameStartAndEnd(String string){
        assertTrue(Task7.isStartAndEndTheSame(string));
    }

    static Arguments[] incorrectStringsWithSameStartAndEnd(){
        return new Arguments[]{
            Arguments.of("1101010"),
            Arguments.of("001"),
            Arguments.of("100"),
            Arguments.of("11010"),
            Arguments.of("100110"),
            Arguments.of(""),
            Arguments.of("2112"),

        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsWithSameStartAndEnd")
    @DisplayName("начинается и заканчивается одним и тем же символом (Negative)")
    void shouldPassValidationWithSameStartAndEnd(String string){
        assertFalse(Task7.isStartAndEndTheSame(string));
    }


    static Arguments[] correctStringsLength(){
        return new Arguments[]{
            Arguments.of("1"),
            Arguments.of("0"),
            Arguments.of("10"),
            Arguments.of("01"),
            Arguments.of("11"),
            Arguments.of("00"),
            Arguments.of("001"),
            Arguments.of("010"),
            Arguments.of("100"),
            Arguments.of("101"),
            Arguments.of("110"),
            Arguments.of("111"),
            Arguments.of("000"),

        };
    }

    @ParameterizedTest
    @MethodSource("correctStringsLength")
    @DisplayName("длина не менее 1 и не более 3 (Positive)")
    void shouldPassValidationLength(String string){
        assertTrue(Task7.isLengthMoreOneNoMoreThree(string));
    }

    static Arguments[] incorrectStringsLength(){
        return new Arguments[]{
            Arguments.of(""),
            Arguments.of("0101"),
            Arguments.of("0101010"),
            Arguments.of("010101"),
            Arguments.of("011010110101"),
            Arguments.of("021"),

        };
    }

    @ParameterizedTest
    @MethodSource("incorrectStringsLength")
    @DisplayName("длина не менее 1 и не более 3 (Negative)")
    void shouldntPassValidationLength(String string){
        assertFalse(Task7.isLengthMoreOneNoMoreThree(string));
    }


}
