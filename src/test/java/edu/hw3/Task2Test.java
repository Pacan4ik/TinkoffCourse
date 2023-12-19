package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    static Arguments[] strings() {
        return new Arguments[] {
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))

        };
    }

    @ParameterizedTest
    @MethodSource("strings")
    void shouldClusterize(String input, List<String> expected) {
        assertEquals(expected, Task2.clusterize(input));
    }

    @Test
    void shouldReturnNullWhenImpossibleToCluster(){
        //given
        String input = "(())(()";

        //when
        List<String> clusters = Task2.clusterize(input);

        //then
        Assertions.assertNull(clusters);
    }

    @Test
    void shouldThrowExceptionWhenStringContainsSomethingElse(){
        //given
        String input = "(()])";

        //then
        Assertions.assertThrows(IllegalArgumentException.class, ()->Task2.clusterize(input));
    }
}
