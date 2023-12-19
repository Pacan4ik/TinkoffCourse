package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task4Test {

    static Arguments[] numbers() {
        return new Arguments[] {
            Arguments.of(1, "I"),
            Arguments.of(3, "III"),
            Arguments.of(4, "IV"),
            Arguments.of(9, "IX"),
            Arguments.of(11, "XI"),
            Arguments.of(24, "XXIV"),
            Arguments.of(37, "XXXVII"),
            Arguments.of(49, "XLIX"),
            Arguments.of(56, "LVI"),
            Arguments.of(65, "LXV"),
            Arguments.of(73, "LXXIII"),
            Arguments.of(88, "LXXXVIII"),
            Arguments.of(101, "CI"),
            Arguments.of(284, "CCLXXXIV"),
            Arguments.of(356, "CCCLVI"),
            Arguments.of(499, "CDXCIX"),
            Arguments.of(545, "DXLV"),
            Arguments.of(637, "DCXXXVII"),
            Arguments.of(777, "DCCLXXVII"),
            Arguments.of(879, "DCCCLXXIX"),
            Arguments.of(931, "CMXXXI"),
            Arguments.of(1234, "MCCXXXIV"),
            Arguments.of(2746, "MMDCCXLVI"),
            Arguments.of(3873, "MMMDCCCLXXIII"),
            Arguments.of(3999, "MMMCMXCIX")
        };
    }

    @ParameterizedTest
    @MethodSource("numbers")
    void shouldConvertToRoman(int input, String expected) {
        Assertions.assertEquals(expected, Task4.convertToRoman(input));
    }
}
