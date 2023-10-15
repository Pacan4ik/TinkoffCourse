package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Task2.Rectangle()),
            Arguments.of(new Task2.Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Task2. Иммутабельность")
    void rectangleArea(Task2.Rectangle rect) {
        var newRect = rect.setWidth(20).setHeight(10);

        assertThat(newRect.area())
            .isEqualTo(200.0);
    }

    @Test
    @DisplayName("Task2. Изменение стороны квадрата возвращает прямоугольник")
    void squareChange() {
        //given
        Task2.Square square = new Task2.Square(10);

        //when
        var rect = square.setHeight(30);

        //when
        assertEquals(rect.getClass(), Task2.Rectangle.class);

    }
}
