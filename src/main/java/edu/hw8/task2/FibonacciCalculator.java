package edu.hw8.task2;

import java.util.concurrent.CountDownLatch;

public class FibonacciCalculator {

    private final int threads;
    volatile long[] fibNumbers;
    private CountDownLatch latch;

    public FibonacciCalculator(int threads) {
        this.threads = threads;
    }

    public long calculate(int number) {
        if (number <= 1) {
            throw new IllegalArgumentException("number must be more than 1");
        }

        fibNumbers = new long[number];
        fibNumbers[0] = 1;
        fibNumbers[1] = 1;
        latch = new CountDownLatch(number - 2);

        ThreadPool threadPool = FixedThreadPool.create(threads);

        for (int i = 2; i < number; i++) {
            threadPool.execute(createFibonacciThread(i));
        }
        threadPool.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.close();
        }
        return fibNumbers[number - 1];
    }

    private Thread createFibonacciThread(int i) {
        return new Thread(() -> {
            while (fibNumbers[i - 1] == 0) {

            }
            fibNumbers[i] = fibNumbers[i - 1] + fibNumbers[i - 2];
            latch.countDown();
        });
    }
}
