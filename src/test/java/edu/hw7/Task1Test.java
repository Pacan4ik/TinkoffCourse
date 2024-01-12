package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    void counterIsThreadSafe() {
        Assertions.assertEquals(
            1000,
            Task1.MultiThreading.incrementWithMultiThreading(4, 250)
        );
    }
}
