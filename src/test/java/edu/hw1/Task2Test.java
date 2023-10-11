package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("1.Количество цифр")
    void numberOfDigitsTest() {
        // given
        int num = 4666;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("2.Количество цифр. Отрицательное число")
    void numberOfDigitsTest2() {
        // given
        int num = -544;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("3.Количество цифр. Ноль тоже цифра")
    void numberOfDigitsTest3() {
        // given
        int num = 0;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(1);
    }

}
