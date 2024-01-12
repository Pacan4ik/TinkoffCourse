package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.TreeMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void shouldAddNull() {
        //given
        TreeMap<String, String> tree = new TreeMap<>(new Task7.NullComparator<>());

        //when
        tree.put(null, "test");

        //then
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    void shouldWorkAsNormalComparatorReflexivity() {
        //given
        Comparator<String> comparator = new Task7.NullComparator<>();

        //then
        assertThat(comparator.compare("str", "str")).isEqualTo(0);
        assertThat(comparator.compare(null, null)).isEqualTo(0);

        assertThat(Math.signum(comparator.compare(null, "str")))
            .isEqualTo(Math.signum(-comparator.compare("str", null)));

    }

    @Test
    void shouldWorkAsNormalComparatorSymmetry() {
        //given
        Comparator<String> comparator = new Task7.NullComparator<>();

        //then
        assertThat(Math.signum(comparator.compare(null, "str")))
            .isEqualTo(Math.signum(-comparator.compare("str", null)));

        assertThat(Math.signum(comparator.compare("null", "str")))
            .isEqualTo(Math.signum(-comparator.compare("str", "null")));

    }

    static Arguments[] Transitivity() {
        return new Arguments[] {
            Arguments.of("abc", "abb", "aba"),
            Arguments.of(null, "abb", "aba")

        };
    }

    @ParameterizedTest
    @MethodSource("Transitivity")
    void shouldWorkAsNormalComparatorTransitivity(String a, String b, String c) {
        //given
        Comparator<String> comparator = new Task7.NullComparator<>();

        //then
        assertThat(comparator.compare(a, c)).isGreaterThan(0);
    }

    static Arguments[] Consistency() {
        return new Arguments[] {
            Arguments.of("abc", "abc", "aba"),
            Arguments.of("abc", "abc", null),
            Arguments.of(null, null, "abc"),
            Arguments.of(null, null, null)

        };
    }

    @ParameterizedTest
    @MethodSource("Consistency")
    void shouldWorkAsNormalComparatorConsistency(String a, String b, String c) {
        //given
        Comparator<String> comparator = new Task7.NullComparator<>();

        //then
        assertThat(Math.signum(comparator.compare(a, c)))
            .isEqualTo(Math.signum(comparator.compare(b, c)));
    }
}
