package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void shouldReturnAnalytics() {
        //given
        String[] sessions = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        };

        //when
        String res = Task1.getComputerClubStats(sessions);

        //then
        Assertions.assertEquals("3ч 40м", res);
    }

    @Test
    void shouldReturnAnalyticsWithOneSession() {
        //given
        String[] sessions = new String[] {
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
        };

        //when
        String res = Task1.getComputerClubStats(sessions);

        //then
        Assertions.assertEquals("3ч 30м", res);
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

    @Test
    void shouldAnalyzeCorrectlyWithLongSessions() {
        //given
        String[] sessions = new String[] {
            "2022-03-12, 23:20 - 2022-03-14, 6:50",
        };
        //when
        String res = Task1.getComputerClubStats(sessions);

        //then
        Assertions.assertEquals("31ч 30м", res);
    }
}
