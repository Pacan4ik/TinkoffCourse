package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("1.Кони на доске")
    void knightCaptureTest() {
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
    @DisplayName("2.Кони на доске")
    void knightCaptureTest2() {
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
    @DisplayName("3.Кони на доске")
    void knightCaptureTest3() {
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
    @DisplayName("4.Кони на доске")
    void knightCaptureTest4() {
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
    @DisplayName("5.Кони на доске. Пустая доска")
    void knightCaptureTest5() {
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
    @DisplayName("6.Кони на доске. Массивы разной длины")
    void knightCaptureTest6() {
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
    @DisplayName("7.Кони на доске. Внутри массива null")
    void knightCaptureTest7() {
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
    @DisplayName("8.Кони на доске. Desk is null")
    void knightCaptureTest8() {
        // given
        int[][] desk = null;

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }
}
