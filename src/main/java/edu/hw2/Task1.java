package edu.hw2;

public final class Task1 {
    private Task1() {
    }

    public record Constant(double num) implements Expr {

        @Override
        public double evaluate() {
            return num;
        }
    }

    public record Negate(Expr num) implements Expr {
        @Override
        public double evaluate() {
            return -num.evaluate();
        }
    }

    public record Exponent(Expr base, double pow) implements Expr {
        @Override
        public double evaluate() {
            return Math.pow(base.evaluate(), pow);
        }
    }

    public record Addition(Expr num1, Expr num2) implements Expr {
        @Override
        public double evaluate() {
            return num1.evaluate() + num2.evaluate();
        }
    }

    public record Multiplication(Expr num1, Expr num2) implements Expr {
        @Override
        public double evaluate() {
            return num1.evaluate() * num2.evaluate();
        }
    }

    public sealed interface Expr {
        double evaluate();
    }
}
