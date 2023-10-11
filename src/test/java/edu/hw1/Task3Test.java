package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Стандартный тест")
    void nestedArrayNestedTest() {
        // given
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {0, 6};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertTrue(isNest);
    }

    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Cтандартный тест 2")
    void nestedArrayNestedTest2() {
        // given
        int[] a1 = {3, 1};
        int[] a2 = {4, 0};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertTrue(isNest);
    }

    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Стандартный тест 3. Массив не вмещается")
    void nestedArrayNotNestedTest() {
        // given
        int[] a1 = {9, 9, 8};
        int[] a2 = {8, 9};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertFalse(isNest);
    }

    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Стандартный тест 4. Массив не вмещается")
    void nestedArrayNotNestedTest2() {
        // given
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {2, 3};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertFalse(isNest);
    }

    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Пустой массив")
    void nestedArrayEmptyTest() {
        // given
        int[] a1 = {};
        int[] a2 = {2, 3};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertFalse(isNest);
    }

    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Оба массива пустые")
    void nestedArrayBothEmptyTest() {
        // given
        int[] a1 = {};
        int[] a2 = {};

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertFalse(isNest);
    }

    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Null")
    void nestedArrayNullTest() {
        // given
        int[] a1 = {1};
        int[] a2 = null;

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertFalse(isNest);
    }

    @Test
    @DisplayName("Вложенный массив (Task3.isNestable). Оба Null")
    void nestedArrayBothNullTest() {
        // given
        int[] a1 = null;
        int[] a2 = null;

        // when
        boolean isNest = Task3.isNestable(a1, a2);

        // then
        assertFalse(isNest);
    }

}
