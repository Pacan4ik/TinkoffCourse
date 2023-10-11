package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Количество цифр (Task2.countDigits). Стандартный подсчёт")
    void numberOfDigitsStandartTest() {
        // given
        int num = 4666;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("Количество цифр (Task2.countDigits). Отрицательное число")
    void numberOfDigitsNegativeTest() {
        // given
        int num = -544;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("Количество цифр (Task2.countDigits). Ноль тоже цифра")
    void numberOfDigitsZeroTest() {
        // given
        int num = 0;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(1);
    }

}
