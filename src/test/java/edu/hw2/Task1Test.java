package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Калькулятор выражений (Task1). Константа")
    void exprCalculatorConst() {
        //given
        double number = 10.45;

        //when
        double exprRes = new Task1.Constant(number).evaluate();

        //then
        assertThat(exprRes)
            .isEqualTo(number);

    }

    @Test
    @DisplayName("Калькулятор выражений (Task1). Сложение")
    void exprCalculatorAddition() {
        //given
        double a = 10.4;
        double b = 5.6;

        //when
        var exprConst1 = new Task1.Constant(a);
        var exprConst2 = new Task1.Constant(b);
        double result = new Task1.Addition(exprConst1, exprConst2).evaluate();

        //then
        assertThat(result)
            .isEqualTo(a + b);

    }

    @Test
    @DisplayName("Калькулятор выражений (Task1). Умножение")
    void exprCalculatorMultiplication() {
        //given
        double a = 10.4;
        double b = 5.6;

        //when
        var exprConst1 = new Task1.Constant(a);
        var exprConst2 = new Task1.Constant(b);
        double result = new Task1.Multiplication(exprConst1, exprConst2).evaluate();

        //then
        assertThat(result)
            .isEqualTo(a * b);

    }

    @Test
    @DisplayName("Калькулятор выражений (Task1). Обратный знак")
    void exprCalculatorNegate() {
        //given
        double num = 10.4;

        //when
        var exprConst = new Task1.Constant(num);
        double result = new Task1.Negate(exprConst).evaluate();

        //then
        assertThat(result)
            .isEqualTo(-num);

    }

    @Test
    @DisplayName("Калькулятор выражений (Task1). Возведение в степень")
    void exprCalculatorExponent() {
        //given
        double base = 4;
        double pow = 3;

        //when
        var exprConst1 = new Task1.Constant(base);
        double result = new Task1.Exponent(exprConst1, pow).evaluate();

        //then
        assertThat(result)
            .isEqualTo(64);

    }

    @Test
    @DisplayName("Калькулятор выражений (Task1). Выражение (-(3 + (-2*4)))^2 = 25")
    void exprCalculatorExpression() {
        //given
        double a = -2;
        double b = 4;
        double c = 3;
        double pow = 2;

        //when
        var exprConst1 = new Task1.Constant(a);
        var exprConst2 = new Task1.Constant(b);
        var exprConst3 = new Task1.Constant(c);

        var multRes = new Task1.Multiplication(exprConst1, exprConst2);
        var addRes = new Task1.Addition(exprConst3, multRes);
        var negRes = new Task1.Negate(addRes);
        var expRes = new Task1.Exponent(negRes, pow);

        double evalRes = expRes.evaluate();

        //then
        assertThat(evalRes)
            .isEqualTo(25);

    }
}
