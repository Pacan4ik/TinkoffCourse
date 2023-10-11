package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). 8 вправо на 1")
    void cyclicShiftEightRightByOneTest() {
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
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). 16 влево на 1")
    void cyclicShiftSixteenLeftByOneTest() {
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
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). 17 влево на 2")
    void cyclicShiftSeventeenLeftByTwoTest() {
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
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). 8 вправо на 3")
    void cyclicShiftEighRightByThreeTest() {
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
    @DisplayName(
        "Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). Восемь влево на 5 (больше чем полный цикл)")
    void cyclicShiftMoreThanEntireCycleTest() {
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
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). Сдвиг на отрицательное число")
    void cyclicShiftNegativeShiftTest() {
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
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). Cдвиг на 0")
    void cyclicShiftZeroShiftTest() {
        // given
        int num = 10;
        int shift = 0;

        // when
        int res = Task7.rotateRight(num, shift);

        // then
        assertThat(res)
            .isEqualTo(num);
    }

    @Test
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). Cдвиг отрицательного числа")
    void cyclicShiftNegativeNumTest() {
        // given
        int num = -10;
        int shift = 1;

        // when
        int res = Task7.rotateRight(num, shift);

        // then
        assertThat(res)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("Циклический битовый сдвиг(Task7.rotateLeft | Task7.rotateRight). Cдвиг отрицательного числа на 0")
    void cyclicShiftNegativeNumShiftByZeroTest() {
        // given
        int num = 0;
        int shift = 0;

        // when
        int res = Task7.rotateRight(num, shift);

        // then
        assertThat(res)
            .isEqualTo(num);
    }
}
