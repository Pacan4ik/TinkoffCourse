package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("1.Постоянная Капрекара")
    void kaprekarsConstTest() {
        // given
        int num = 3542;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("2.Постоянная Капрекара. Отрицательное число")
    void kaprekarsConstTest2() {
        // given
        int num = -3542;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("3.Постоянная Капрекара. Меньше четырех цифр")
    void kaprekarsConstTest3() {
        // given
        int num = 2;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("4.Постоянная Капрекара. Больше четырех цифр")
    void kaprekarsConstTest4() {
        // given
        int num = 25463;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("5.Постоянная Капрекара.")
    void kaprekarsConstTest5() {
        // given
        int num = 5732;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(7);
    }

    @Test
    @DisplayName("6.Постоянная Капрекара.")
    void kaprekarsConstTest6() {
        // given
        int num = 6174;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(0);
    }

}
