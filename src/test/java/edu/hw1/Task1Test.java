package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Перевод в секунды (Task1.minutesToSeconds). Стандартный подсчёт")
    void convertToSecondsStandartTest() {
        // given
        String time = "3:06";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(186);
    }

    @Test
    @DisplayName("Перевод в секунды (Task1.minutesToSeconds). Граничное условие 0:00")
    void convertToSecondsZeroTimeTest() {
        // given
        String time = "0:00";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Перевод в секунды (Task1.minutesToSeconds). Неверный формат")
    void convertToSecondsInvalidFormatTest() {
        // given
        String time = "3:60";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Перевод в секунды (Task1.minutesToSeconds). Больше чем две цифры в минутах")
    void convertToSecondsMoreDigitsTest() {
        // given
        String time = "333:59";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(20039);
    }

    @Test
    @DisplayName("Перевод в секунды (Task1.minutesToSeconds). Слишком большое кол-во минут")
    void convertToSecondsTooBigTest() {
        // given
        String time = Integer.MAX_VALUE + ":59";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(-1);
    }
}
