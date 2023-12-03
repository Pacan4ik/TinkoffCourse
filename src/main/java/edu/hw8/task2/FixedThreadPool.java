package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.jetbrains.annotations.NotNull;

public class FixedThreadPool implements ThreadPool {

    private final Thread[] threads;
    private final BlockingQueue<Runnable> taskQueue;

    private FixedThreadPool(int threads) {
        this.threads = new Thread[threads];
        this.taskQueue = new LinkedBlockingDeque<>();
    }

    public static ThreadPool create(int threads) {
        if (threads < 1) {
            throw new IllegalArgumentException("Illegal value for threads");
        }
        return new FixedThreadPool(threads);
    }

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (!taskQueue.isEmpty() && !Thread.currentThread().isInterrupted()) {
                    try {
                        Runnable task = taskQueue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                }
            });
            threads[i].start();
        }

    }

    @Override
    public void execute(@NotNull Runnable runnable) {
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        for (var t : threads) {
            if (t != null) {
                t.interrupt();
            }
        }
    }
}
