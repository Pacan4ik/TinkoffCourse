package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    static Arguments[] fridaysInYear() {
        return new Arguments[] {
            Arguments.of(
                2024,
                List.of(
                    LocalDate.parse("2024-09-13"),
                    LocalDate.parse("2024-12-13")
                )
            ),
            Arguments.of(
                1925,
                List.of(
                    LocalDate.parse("1925-02-13"),
                    LocalDate.parse("1925-03-13"),
                    LocalDate.parse("1925-11-13")
                )
            )
        };

    }

    @ParameterizedTest
    @MethodSource("fridaysInYear")
    void shouldFindFridays(int year, List<LocalDate> expectedDates) {
        Assertions.assertThat(Task2.getBadFridays(year))
            .containsExactlyInAnyOrderElementsOf(expectedDates);

    }

    @Test
    void shouldThrowExceptionWithWrongYear() {
        //given
        int year = -10;

        assertThrows(IllegalArgumentException.class, () -> Task2.getBadFridays(year));
    }

    static Arguments[] dates() {
        return new Arguments[] {
            Arguments.of(LocalDate.parse("2024-01-02"), LocalDate.parse("2024-09-13")),
            Arguments.of(LocalDate.parse("2020-11-21"), LocalDate.parse("2021-08-13")),

        };
    }

    @ParameterizedTest
    @MethodSource("dates")
    void shouldFindNextFriday(LocalDate current, LocalDate expected) {
        assertEquals(expected, Task2.getNextBadFriday(current));
    }
}
