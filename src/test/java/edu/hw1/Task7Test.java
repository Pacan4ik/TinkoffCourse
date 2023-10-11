package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("1.Циклический битовый сдвиг")
    void cyclicShiftTest() {
        // given
        int num = 8;
        int shift = 1;

        // when
        int res = Task7.rotateRight(num, shift);

        // then
        assertThat(res)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("2.Циклический битовый сдвиг")
    void cyclicShiftTest2() {
        // given
        int num = 16;
        int shift = 1;

        // when
        int res = Task7.rotateLeft(num, shift);

        // then
        assertThat(res)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("3.Циклический битовый сдвиг")
    void cyclicShiftTest3() {
        // given
        int num = 17;
        int shift = 2;

        // when
        int res = Task7.rotateLeft(num, shift);

        // then
        assertThat(res)
            .isEqualTo(6);
    }

    @Test
    @DisplayName("4.Циклический битовый сдвиг")
    void cyclicShiftTest4() {
        // given
        int num = 8;
        int shift = 3;

        // when
        int res = Task7.rotateRight(num, shift);

        // then
        assertThat(res)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("5.Циклический битовый сдвиг")
    void cyclicShiftTest5() {
        // given
        int num = 8;
        int shift = 5;

        // when
        int res = Task7.rotateLeft(num, shift);

        // then
        assertThat(res)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("6.Циклический битовый сдвиг. Сдвиг на отрицательное число")
    void cyclicShiftTest6() {
        // given
        int num = 8;
        int shift = -1;

        // when
        int res = Task7.rotateRight(num, shift);

        // then
        assertThat(res)
            .isEqualTo(1);
    }
    @Test
    @DisplayName("7.Циклический битовый сдвиг. Cдвиг на 0")
    void cyclicShiftTest7() {
        // given
        int num = 10;
        int shift = 0;

        // when
        int res = Task7.rotateRight(num, shift);

        // then
        assertThat(res)
            .isEqualTo(num);
    }


}
