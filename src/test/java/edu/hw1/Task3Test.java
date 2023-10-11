package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("1.Вложенный массив.")
    void nestedArrayTest() {
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
    @DisplayName("2.Вложенный массив")
    void nestedArrayTest2() {
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
    @DisplayName("3.Вложенный массив")
    void nestedArrayTest3() {
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
    @DisplayName("4.Вложенный массив")
    void nestedArrayTest4() {
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
    @DisplayName("5.Вложенный массив. Пустой массив")
    void nestedArrayTest5() {
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
    @DisplayName("6.Вложенный массив. Null")
    void nestedArrayTest6() {
        // given
        int[] a1 = {1};
        int[] a2 = null;

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertThat(isNest)
            .isEqualTo(false);
    }

}
