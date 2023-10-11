package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Особый палиндром(Task5.isPalindromeDescendant). Стандартный тест")
    void descendantPalindromeStandartTest() {
        // given
        int pal = 11211230;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Особый палиндром(Task5.isPalindromeDescendant). Cтанадртый тест 2")
    void descendantPalindromeStandartTest2() {
        // given
        int pal = 13001120;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Особый палиндром(Task5.isPalindromeDescendant). Сразу палиндром")
    void descendantPalindromeAlreadyTest() {
        // given
        int pal = 11;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Особый палиндром(Task5.isPalindromeDescendant). Нечетное количество знаков")
    void descendantPalindromeOddTest() {
        // given
        int pal = 35753;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(true);
    }

    @Test
    @DisplayName("Особый палиндром(Task5.isPalindromeDescendant). Не палиндром")
    void descendantPalindromeFalseTest() {
        // given
        int pal = 12345;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(false);
    }
}
