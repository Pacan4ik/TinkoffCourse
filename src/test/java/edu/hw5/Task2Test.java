package edu.hw5;

import java.time.LocalDate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @Test
    void shouldFindFridays() {
        //given
        int year = 1925;

        //when
        var fridays = Task2.getBadFridays(year);

        //then
        Assertions.assertThat(fridays).extracting(LocalDate::toString)
            .containsExactlyInAnyOrderElementsOf(List.of("1925-02-13", "1925-03-13", "1925-11-13"));

    }

    @Test
    void shouldThrowExceptionWithWrongYear() {
        //given
        int year = -10;

        assertThrows(IllegalArgumentException.class, () -> Task2.getBadFridays(year));
    }

    @Test
    void shouldFindNextFriday() {
        //given
        LocalDate localDate = LocalDate.of(2024, 1, 2);

        //when
        LocalDate nextFriday = Task2.getNextBadFriday(localDate);

        //then
        assertEquals(nextFriday, LocalDate.of(2024, 9, 13));
    }

}
