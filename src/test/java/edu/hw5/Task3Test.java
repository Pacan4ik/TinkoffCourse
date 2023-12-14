package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    static Arguments[] arguments() {
        return new Arguments[] {
            Arguments.of("2020-10-10", LocalDate.of(2020, 10, 10)),
            Arguments.of("2020-12-2", LocalDate.of(2020, 12, 2)),
            Arguments.of("1/3/1976", LocalDate.of(1976, 3, 1)),
            Arguments.of("1/3/20", LocalDate.of(2020, 3, 1)),
            Arguments.of("tomorrow", LocalDate.now().plusDays(1)),
            Arguments.of("today", LocalDate.now()),
            Arguments.of("yesterday", LocalDate.now().plusDays(-1)),
            Arguments.of("1 day ago", LocalDate.now().plusDays(-1)),
            Arguments.of("2234 days ago", LocalDate.now().plusDays(-2234)),
        };
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void shouldConvertCorrectly(String string, LocalDate expectedDate) {
        assertEquals(expectedDate, Task3.parseDate(string).get());
    }

    @Test
    void shouldReturnOptionalEmptyIfUnexpectedFormat() {
        //given
        String string = "in 1 day";

        //when
        var date = Task3.parseDate(string);

        //then
        assertEquals(Optional.empty(), date);

    }
}
