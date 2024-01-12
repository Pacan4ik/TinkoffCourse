package edu.hw8;

import edu.hw8.task2.FibonacciCalculator;
import edu.hw8.task2.FixedThreadPool;
import edu.hw8.task2.ThreadPool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;


public class Task2Test {
    @RepeatedTest(200)
    void test() {
        //given
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator(5);

        //when
        long n = fibonacciCalculator.calculate(20);

        //then
        Assertions.assertEquals(6765, n);
    }

}
