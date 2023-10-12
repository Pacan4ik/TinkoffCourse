package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Стандартный тест")
    void kaprekarsConstStandartTest() {
        // given
        int num = 3542;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Отрицательное число")
    void kaprekarsConstNegativeTest() {
        // given
        int num = -3542;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Меньше четырех цифр")
    void kaprekarsConstLessThanFourDigitsTest() {
        // given
        int num = 2;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Больше четырех цифр")
    void kaprekarsConstMoreThanFourDigitsTest() {
        // given
        int num = 25463;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Максимальное количество шагов")
    void kaprekarsConstMaxStepsTest() {
        // given
        int num = 5732;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(7);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Число и есть постоянная Капрекара")
    void kaprekarsConstAlreadyConstTest() {
        // given
        int num = 6174;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Граничный случай 1000")
    void kaprekarsConstThousandTest() {
        // given
        int num = 1000;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(5);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Граничный случай 9999 (все цифры одинаковые)")
    void kaprekarsConstMaxNumTest() {
        // given
        int num = 9999;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Постоянная Капрекара (Task6.countK). Граничный случай 9998")
    void kaprekarsConstMaxNumThatCanBeCountedTest() {
        // given
        int num = 9998;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(5);
    }
}
