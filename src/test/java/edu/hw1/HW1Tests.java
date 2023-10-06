package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HW1Tests {
    @Test
    @DisplayName("1.Перевод в секунды")
    void convertToSeconds() {
        // given
        String time = "3:06";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(186);
    }

    @Test
    @DisplayName("2.Перевод в секунды")
    void convertToSeconds2() {
        // given
        String time = "0:00";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("3.Перевод в секунды")
    void convertToSeconds3() {
        // given
        String time = "3:60";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("4.Перевод в секунды")
    void convertToSeconds4() {
        // given
        String time = "333:59";

        // when
        int seconds = Task1.minutesToSecond(time);

        // then
        assertThat(seconds)
            .isEqualTo(20039);
    }

    @Test
    @DisplayName("1.Количество цифр")
    void numberOfDigits() {
        // given
        int num = 4666;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(4);
    }

    @Test
    @DisplayName("2.Количество цифр")
    void numberOfDigits2() {
        // given
        int num = -544;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("3.Количество цифр")
    void numberOfDigits3() {
        // given
        int num = 0;

        // when
        int digits = Task2.countDigits(num);

        // then
        assertThat(digits)
            .isEqualTo(1);
    }

    @Test
    @DisplayName("1.Вложенный масив")
    void nestedArray() {
        // given
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertThat(isNest)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("2.Вложенный масив")
    void nestedArray2() {
        // given
        int[] a1 = {3, 1};
        int[] a2 = {4, 0};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertThat(isNest)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("3.Вложенный масив")
    void nestedArray3() {
        // given
        int[] a1 = {9, 9, 8};
        int[] a2 = {8, 9};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertThat(isNest)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("4.Вложенный масив")
    void nestedArray4() {
        // given
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {2, 3};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertThat(isNest)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("5.Вложенный масив")
    void nestedArray5() {
        // given
        int[] a1 = {};
        int[] a2 = {2, 3};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertThat(isNest)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("6.Вложенный масив")
    void nestedArray6() {
        // given
        int[] a1 = {1};
        int[] a2 = null;

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertThat(isNest)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("1.Сломанная строка")
    void brokenStr() {
        // given
        String str = "123456";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("214365");
    }

    @Test
    @DisplayName("2.Сломанная строка")
    void brokenStr2() {
        // given
        String str = "hTsii  s aimex dpus rtni.g";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("3.Сломанная строка")
    void brokenStr3() {
        // given
        String str = "badce";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("abcde");
    }

    @Test
    @DisplayName("4.Сломанная строка")
    void brokenStr4() {
        // given
        String str = "";

        // when
        String fixed = Task4.fixString(str);

        // then
        assertThat(fixed)
            .isEqualTo("");
    }

    @Test
    @DisplayName("1.Особый палиндром")
    void specialPalindrome() {
        // given
        int pal = 11211230;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("2.Особый палиндром")
    void specialPalindrome2() {
        // given
        int pal = 13001120;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("3.Особый палиндром")
    void specialPalindrome3() {
        // given
        int pal = 23336014;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("4.Особый палиндром")
    void specialPalindrome4() {
        // given
        int pal = 11;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("5.Особый палиндром")
    void specialPalindrome5() {
        // given
        int pal = 35753;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("6.Особый палиндром")
    void specialPalindrome6() {
        // given
        int pal = 1234567432;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(false);
    }

    @Test
    @DisplayName("1.Постоянная Капрекара")
    void KaprekarsConst() {
        // given
        int num = 3542;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(3);
    }

    @Test
    @DisplayName("2.Постоянная Капрекара")
    void KaprekarsConst2() {
        // given
        int num = -3542;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("3.Постоянная Капрекара")
    void KaprekarsConst3() {
        // given
        int num = 2;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(-1);
    }

    @Test
    @DisplayName("4.Постоянная Капрекара")
    void KaprekarsConst4() {
        // given
        int num = 5732;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(7);
    }

    @Test
    @DisplayName("5.Постоянная Капрекара")
    void KaprekarsConst5() {
        // given
        int num = 6174;

        // when
        int steps = Task6.countK(num);

        // then
        assertThat(steps)
            .isEqualTo(0);
    }

    @Test
    @DisplayName("1.Циклический битовый сдвиг")
    void cyclicShift() {
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
    void cyclicShift2() {
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
    void cyclicShift3() {
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
    void cyclicShift4() {
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
    void cyclicShift5() {
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
    @DisplayName("1.Кони на доске")
    void knightCapture() {
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
    void knightCapture2() {
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
    void knightCapture3() {
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
    void knightCapture4() {
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
    @DisplayName("5.Кони на доске")
    void knightCapture5() {
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
    @DisplayName("6.Кони на доске")
    void knightCapture6() {
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
    @DisplayName("7.Кони на доске")
    void knightCapture7() {
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
    @DisplayName("8.Кони на доске")
    void knightCapture8() {
        // given
        int[][] desk = null;

        // when
        boolean res = Task8.knightBoardCapture(desk);

        // then
        assertThat(res)
            .isEqualTo(false);
    }

}
