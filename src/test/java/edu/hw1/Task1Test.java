package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("1.Перевод в секунды")
    void convertToSecondsTest() {
        // given
        String time = "3:06";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(186);
    }

    @Test
    @DisplayName("2.Перевод в секунды. Граничное условие 0:00")
    void convertToSecondsTest2() {
        // given
        String time = "0:00";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("3.Перевод в секунды. Неверный формат")
    void convertToSecondsTest3() {
        // given
        String time = "3:60";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("4.Перевод в секунды. Больше чем две цифры в минутах")
    void convertToSecondsTest4() {
        // given
        String time = "333:59";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(20039);
    }

    @Test
    @DisplayName("5.Перевод в секунды. Слишком большое кол-во минут")
    void convertToSecondsTest5() {
        // given
        String time = Integer.MAX_VALUE + ":59";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(-1);
    }
}
