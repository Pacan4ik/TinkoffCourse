package edu.hw10.task2.fibcalc;

public class SimpleRecursiveFibCalculator implements FibCalculator {

    @Override
    public long fib(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("num must be a natural number");
        }
        if (num == 1 || num == 2) {
            return 1;
        }
        return fib(num - 2) + fib(num - 1);
    }
}
