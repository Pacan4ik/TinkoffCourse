package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Стандартный тест")
    void knightCaptureStandartTest() {
        // given
        int[][] desk = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("2.Кони на доске (Task8.knightBoardCapture). Стандартный тест 2")
    void knightCaptureStandartTest2() {
        // given
        int[][] desk = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Стандартный тест 3")
    void knightCaptureStandartTest3() {
        // given
        int[][] desk = new int[][] {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Два коня.")
    void knightCaptureTwoKnightTest() {
        // given
        int[][] desk = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Пустая доска (из 0)")
    void knightCaptureNoKnightsTest() {
        // given
        int[][] desk = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Массивы разной длины")
    void knightCaptureDifferentLengthsArraysTest() {
        // given
        int[][] desk = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Внутри массива null")
    void knightCaptureOneOfLineIsNullTest() {
        // given
        int[][] desk = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            null,
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Desk is null")
    void knightCaptureNullTest() {
        // given
        int[][] desk = null;

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("Кони на доске (Task8.knightBoardCapture). Строк больше 8")
    void knightCaptureStrangeDeskNullTest() {
        // given
        int[][] desk = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }
}
