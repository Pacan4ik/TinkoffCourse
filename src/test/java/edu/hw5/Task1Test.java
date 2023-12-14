package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    static Arguments[] sessionsData() {
        return new Arguments[] {
            Arguments.of(
                new String[] {
                    "2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"
                },
                "3ч 40м"
            ),

            Arguments.of(
                new String[] {
                    "2022-03-12, 20:20 - 2022-03-12, 23:50"
                },
                "3ч 30м"
            ),
            Arguments.of(
                new String[] {
                    "2022-03-12, 23:20 - 2022-03-14, 6:50"
                },
                "31ч 30м"
            )

        };
    }

    @ParameterizedTest
    @MethodSource("sessionsData")
    void shouldReturnAnalytics(String[] sessions, String expected) {
        assertEquals(
            expected,
            Task1.getComputerClubStats(sessions)
        );
    }

    @Test
    void shouldThrowExceptionIfIncorrectFormat() {
        //given
        String[] sessions = new String[] {
            "2022.03.12, 20:20 - 2022.03.12, 23:50",
        };

        //then
        Assertions.assertThrows(Exception.class, () -> Task1.getComputerClubStats(sessions));
    }

}
