package edu.hw10.task2.fibcalc;

import edu.hw10.task2.Cache;

public interface FibCalculator {
    @Cache(persist = true, path = "cached")
    long fib(int num);
}
