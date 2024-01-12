package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {

    static Arguments[] correctSubseqs(){
        return new Arguments[]{
            Arguments.of("abc", "qweabcde"),
            Arguments.of("f\\d", "asdf\\dsa"),
            Arguments.of("abc", "abcde"),
            Arguments.of("a", "a"),
            Arguments.of("abc", "qweabc"),
            Arguments.of("", ""),
            Arguments.of("", "abc"),

        };
    }
    @ParameterizedTest
    @MethodSource("correctSubseqs")
    @DisplayName("S является подпоследовательностью строки T")
    void shouldSpotSubsequence(String s, String t) {
        assertTrue(Task6.isSubsequence(s,t));
    }


    static Arguments[] incorrectSubseqs(){
        return new Arguments[]{
            Arguments.of("i", "abcde"),
            Arguments.of("abcdd", "abcde"),
        };
    }
    @ParameterizedTest
    @MethodSource("incorrectSubseqs")
    @DisplayName("S не является подпоследовательностью строки T")
    void shouldntSpotSubsequence(String s, String t) {
        assertFalse(Task6.isSubsequence(s,t));
    }

}
