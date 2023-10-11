package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("1.Особый палиндром")
    void descendantPalindromeTest() {
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
    void descendantPalindromeTest2() {
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
    void descendantPalindromeTest3() {
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
    void descendantPalindromeTest4() {
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
    void descendantPalindromeTest5() {
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
    void descendantPalindromeTest6() {
        // given
        int pal = 1234567432;

        // when
        boolean isPal = Task5.isPalindromeDescendant(pal);

        // then
        assertThat(isPal)
            .isEqualTo(false);
    }
}
